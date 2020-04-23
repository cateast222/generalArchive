package com.ebs.platform.business.service.impl;

import com.ebs.platform.business.dao.professiondao.IArchiveBorrowItemDao;
import com.ebs.platform.business.dao.professiondao.IBorrowApplyDao;
import com.ebs.platform.business.domain.professionentity.ArchiveBorrowItem;
import com.ebs.platform.business.domain.professionentity.BorrowApply;
import com.ebs.platform.business.dto.archive.ArchiveBorrowByFind;
import com.ebs.platform.business.dto.archive.ArchiveBorrowItemDto;
import com.ebs.platform.business.dto.archive.BorrowApplyDto;
import com.ebs.platform.business.mapper.BorrowApplyMapper;
import com.ebs.platform.business.mapper.BorrowItemMapper;
import com.ebs.platform.business.service.IBorrowApplyService;
import com.ebs.platform.core.BaseEntity;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.query.QueryRequest;
import com.ebs.platform.core.query.QueryResponse;
import com.ebs.platform.core.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BorrowApplyServiceImpl extends BaseEntity implements IBorrowApplyService {

    @Autowired
    private IBorrowApplyDao iBorrowApplyDao;

    @Autowired
    private IArchiveBorrowItemDao iArchiveBorrowItemDao;

    @Autowired
    private BorrowApplyMapper borrowApplyMapper;

    @Autowired
    private BorrowItemMapper borrowItemMapper;


    @Override
    public String add(BorrowApplyDto borrowApplyDto){
        borrowApplyDto.setId(null);//防止前端误传
        BorrowApply applyDO = borrowApplyMapper.DtoToDo(borrowApplyDto);

        applyDO.setBorrowTime(new Date());
        if(applyDO.getBorrowType() == 0){
            applyDO.setReturnStatus(null);
            applyDO.setActualTime(null);
        }
        int i = 0;
        if (applyDO.getBorrowType() == 0){
            applyDO.setUserName(DateUtil.GetAllDateCodeString());
            i=1;
        }else {
            applyDO.setReturnStatus(0);
            i=2;
        }
        iBorrowApplyDao.save(applyDO);

        for(ArchiveBorrowItemDto dto : borrowApplyDto.getArchiveBorrowItemDtoList()){
            dto.setBorrowRegisterId(null);//防止前端误传
            ArchiveBorrowItem borrowItem = borrowItemMapper.DtoToDo(dto);
            borrowItem.setBorrowRegisterId(applyDO.getId());
            iArchiveBorrowItemDao.save(borrowItem);
        }
        if(i == 1){
            return "账号："+applyDO.getUserName();
        }else {
            return null;
        }
    }

    @Override
    public QueryResponse<List<BorrowApplyDto>> queryByFilter(QueryRequest<ArchiveBorrowByFind> req){

        Page<BorrowApply> borrowApplyPage = null;
        if(StringUtils.isNotBlank(req.getSearchText())){
            borrowApplyPage = iBorrowApplyDao.findByDeletedAndBorrowerContainingAndUserNameContaining(false,req.getSearchText(),req.getSearchText(),req.toPageable());
        }else {
            ArchiveBorrowByFind find = req.getCondition();
            borrowApplyPage = iBorrowApplyDao.findAll(new Specification<BorrowApply>() {
                @Override
                public Predicate toPredicate(Root<BorrowApply> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    List<Predicate> list = new ArrayList<>();
                    list.add(cb.equal(root.get("deleted"),false));
                    if(find.getBorrowType() != null){
                        list.add(cb.equal(root.get("borrowType"),find.getBorrowType()));
                    }
                    if(find.getReturnStatus() != null){
                        list.add(cb.equal(root.get("returnStatus"),find.getReturnStatus()));
                    }
                    if(find.getBorrowTime() != null){
                        Date startDate = DateUtil.getDayStartTime(find.getBorrowTime());
                        Date endDate = DateUtil.getDayEndTime(find.getBorrowTime());
                        list.add(cb.between(root.get("borrowTime"),startDate,endDate));
                    }
                    if(find.getReturnTime() !=null){
                        Date startDate = DateUtil.getDayStartTime(find.getReturnTime());
                        Date endDate = DateUtil.getDayEndTime(find.getReturnTime());
                        list.add(cb.between(root.get("returnTime"),startDate,endDate));
                    }
                    if(StringUtils.isNotBlank(find.getPhone())){
                       // list.add(cb.equal(root.get("phone"),find.getPhone()));
                    }
                    if(StringUtils.isNotBlank(find.getBorrower())){
                        list.add(cb.like(root.get("borrower"),"%"+find.getBorrower()+"%"));
                    }
                    if(StringUtils.isNotBlank(find.getBeBorrower())){
//                        List<String> ids = iArchiveBorrowItemDao.nativeFindByBeBorrower(find.getBeBorrower());
//                        if(ids == null || ids.size() == 0) throw new BusinessException("未找到，请检查输入是否准确（可能含有空格）");
//                        list.add((root.get("id").in(ids)));//空的会报错
                    }
                    if (list.size() != 0){
                        Predicate[] p = new Predicate[list.size()];
                        return cb.and(list.toArray(p));
                    }else {
                        return null;
                    }
                }
            },req.toPageable());
        }

        List<BorrowApplyDto> borrowApplyDtos = borrowApplyMapper.DoToDto(borrowApplyPage.getContent());

        return new QueryResponse(borrowApplyPage,borrowApplyDtos);
    }


    @Override
    public BorrowApplyDto queryById(Integer id){
        //TODO 等档案接口
        return null;
    }


      //借阅归还
    @Override
    public void doReturn(Integer id) {
        BorrowApply borrowApply = iBorrowApplyDao.findByIdAndDeleted(id,false);
        if(borrowApply.getBorrowType() == 0)throw new BusinessException("在线借阅无需手动归还");
        if(borrowApply.getReturnStatus() == 1)throw new BusinessException("已归还，请勿重复操作");
        borrowApply.setReturnStatus(1);
        borrowApply.setActualTime(new Date());
        iBorrowApplyDao.save(borrowApply);
    }

    @Override
    public void remove(Integer id){
        BorrowApply borrowApply = iBorrowApplyDao.getOne(id);
        borrowApply.setDeleted(true);
        iBorrowApplyDao.save(borrowApply);
    }

    @Override
    public void reminderReturn(Integer id){
        BorrowApply borrowApply = iBorrowApplyDao.findByIdAndDeleted(id,false);
        borrowApply.setReminderCount(borrowApply.getReminderCount()+1);
        iBorrowApplyDao.save(borrowApply);
    }

}
