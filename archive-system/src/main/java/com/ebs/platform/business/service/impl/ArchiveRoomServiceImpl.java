package com.ebs.platform.business.service.impl;

import com.alibaba.fastjson.JSON;
import com.ebs.platform.business.dao.IArchiveRoomDao;
import com.ebs.platform.business.dao.professiondao.ArchivesDao;
import com.ebs.platform.business.domain.professionentity.ArchiveRoom;
import com.ebs.platform.business.domain.professionentity.Archives;
import com.ebs.platform.business.dto.archive.ArchiveRoomByAdd;
import com.ebs.platform.business.dto.archive.ArchiveRoomDTO;
import com.ebs.platform.business.mapper.ArchiveRoomMapper;
import com.ebs.platform.business.service.IArchiveRoomService;
import com.ebs.platform.core.exception.BusinessException;
import com.ebs.platform.core.service.impl.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lwy
 * @Date 2019-06-06 11:40
 */
@Service
public class ArchiveRoomServiceImpl extends BaseService implements IArchiveRoomService {


    @Autowired
    private ArchivesDao iArchivesDao;

    @Autowired
    private IArchiveRoomDao iArchiveRoomDao;

    @Autowired
    private ArchiveRoomMapper archiveRoomMapper;

    @Override
    public Integer add(ArchiveRoomByAdd roomDTO) {
        ArchiveRoom room;
        if (StringUtils.isEmpty(roomDTO.getId())) {
            room = archiveRoomMapper.DTOtoDO(roomDTO);
            room.setOperator(getUserContext().getUserId());
            room.setCreateTime(new Date());
//            Integer sort = iArchiveRoomDao.nativeFindMaxSort();
//            if(sort == null ){
//                room.setSort(1);
//            }else {
//                room.setSort(sort);
//            }

        } else { //这是修改接口
            room = iArchiveRoomDao.queryByIdAndDeleted(roomDTO.getId(), false);
            room.setName(roomDTO.getName());
            room.setRemark(roomDTO.getRemark());
            room.setUpdateMan(getUserContext().getUserId());
            room.setUpdateTime(new Date());
        }
        iArchiveRoomDao.save(room);
        return room.getId();
    }

    @Override
    public void update(ArchiveRoomByAdd roomDTO) {
        this.add(roomDTO);
    }

    @Override
    @Transactional
    public void remove(Integer id) {
        //需要验证一下是否被使用，级联删除下面的

        ArchiveRoom room = iArchiveRoomDao.queryByIdAndDeleted(id, false);
        if (room == null) throw new BusinessException("未找到");
        room.setDeleted(true);
        iArchiveRoomDao.save(room);

        //List<ArchiveRoomDO> roomDOS = iArchiveRoomDao.nativeFindChildById(id);
        //if(roomDOS != null && roomDOS.size() != 0){
        //List<String> ids =
        //}
    }

    @Override
    public Object findAll() {
        List<ArchiveRoom> roomDOS = iArchiveRoomDao.findByDeleted(false);

//        List<ArchiveRoomDTO> rootMenu = archiveRoomMapper.DOtoDTO(roomDOS);
//        List<ArchiveRoomDTO> menuList = new ArrayList<>();
//        for (int i =0; i<rootMenu.size();  i++){
//            if (StringUtils.isEmpty(rootMenu.get(i).getParentId())){
//                menuList.add(rootMenu.get(i));
//            }
//        }
//        for(ArchiveRoomDTO menu : menuList){
//            menu.setChildren(getChild(menu.getId(),rootMenu));
//        }
//        Map<String,Object> jsonMap = new HashMap<>();
//        jsonMap.put("menu",menuList);
//        return JSON.toJSON(menuList);

        return doFindAll(roomDOS);

    }

    public Object findAllBySelect() {
        List<ArchiveRoom> roomDOS = iArchiveRoomDao.findByDeleted(false);
        //取出第三级的柜子
        List<ArchiveRoom> threeRankRooms = roomDOS.stream().filter(x -> x.getTheRank().equals(3)).collect(Collectors.toList());
        int number = 100000001; //实际数据库自动递增也不会有这么多数据
        for (ArchiveRoom roomParent : threeRankRooms) {
            Integer row = Integer.valueOf(roomParent.getTheRow());
            Integer column = Integer.valueOf(roomParent.getTheColumn());
            for (int x = 1; x <= row; x++) {

                for (int y = 1; y <= column; y++) {
                    ArchiveRoom roomChild = new ArchiveRoom();

                    roomChild.setId(number++);
                    roomChild.setName(x + "层" + y + "列");
                    roomChild.setTheRow(String.valueOf(x));
                    roomChild.setTheColumn(String.valueOf(y));
                    roomChild.setParentId(roomParent.getId());
                    roomChild.setTheRank(4);
                    roomChild.setAmount(roomParent.getAmount());
                    roomDOS.add(roomChild);
                }
            }
        }

        return doFindAll(roomDOS);
    }


    private Object doFindAll(List<ArchiveRoom> roomDOS) {

        List<ArchiveRoomDTO> rootMenu = archiveRoomMapper.DOtoDTO(roomDOS);
        List<ArchiveRoomDTO> menuList = new ArrayList<>();
        for (int i = 0; i < rootMenu.size(); i++) {
            if (StringUtils.isEmpty(rootMenu.get(i).getParentId())) {
                menuList.add(rootMenu.get(i));
            }
        }
        for (ArchiveRoomDTO menu : menuList) {
            menu.setChildren(getChild(menu.getId(), rootMenu));
        }
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("menu", menuList);
        return JSON.toJSON(menuList);

    }


    private List<ArchiveRoomDTO> getChild(Integer id, List<ArchiveRoomDTO> rootMenu) {
        List<ArchiveRoomDTO> childList = new ArrayList<>();
        for (ArchiveRoomDTO menu : rootMenu) {
            if (!StringUtils.isEmpty(menu.getParentId())) {
                if (menu.getParentId().equals(id)) {
                    childList.add(menu);
                }
            }
        }
        for (ArchiveRoomDTO menu : childList) {
            menu.setChildren(getChild(menu.getId(), rootMenu));
        }
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    //根据柜子id 查询每个格子的占用人数（例如柜子有5行5列，那就可以理解为有25个格子，每个格子可以放很多人的）
    @Override
    public List<Map> findUseById(Integer id) {
        //统计分组
        List<Map> mapList = iArchiveRoomDao.findByCabinet(id);//到花名册表去找
        return mapList;
    }

    @Override
    public void archiveMove(List<Archives> archivesDto) {
        // 档案id 柜子id 行，列

        List<Integer> ids = archivesDto.stream().map(Archives::getId).collect(Collectors.toList());
        List<Archives> archivesEntityList = iArchivesDao.findByDeletedAndIdIn(false,ids);
        for(Archives entity : archivesEntityList){

            Archives dto = archivesDto.stream().filter(x -> x.getId().equals(entity.getId())).collect(Collectors.toList()).get(0);
            entity.setArchiveCabinetId(dto.getArchiveCabinetId());
            entity.setArchiveRow(dto.getArchiveRow());
            entity.setArchiveColumn(dto.getArchiveColumn());
        }
        iArchivesDao.saveAll(archivesEntityList);
    }
}