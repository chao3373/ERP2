package com.shenke.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;

import com.shenke.entity.*;
import com.shenke.repository.*;
import com.shenke.util.DateUtil;
import com.shenke.util.EntityUtils;
import com.shenke.util.StringUtil;
import org.apache.commons.collections.list.PredicatedList;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shenke.service.StorageService;

/**
 * 入库单Service实现类
 *
 * @author Administrator
 */
@Service("storageService")
@Transactional
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageRepository storageRepository;

    @Resource
    private SaleListProductRepository saleListProductRepository;

    @Resource
    private JitaiProductionAllotRepository jitaiProductionAllotRepository;

    @Resource
    private JiTaiRepository jiTaiRepository;

    @Resource
    private ClerkRepository clerkRepository;

    @Resource
    private GroupRepository groupRepository;

    @Override
    public void add(Storage storage, String clerkName, String groupName) {

        Group group = groupRepository.findByGrouptName(groupName);
        SaleListProduct saleListProduct = saleListProductRepository.findOne(storage.getSaleListProduct().getId());
        Integer count = saleListProduct.getAccomplishNumber();
        Clerk clerk = clerkRepository.findByNam(clerkName);
        Double realityweight = storage.getRealityweight();


        if (count == null || count == 0) {
            count = 1;
            saleListProductRepository.updateAccomplishNumberById(count, saleListProduct.getId());
        } else if (count == saleListProduct.getNum() - 1) {
            count += 1;
            saleListProductRepository.updateAccomplishNumberById(count, saleListProduct.getId());
            saleListProductRepository.updateState("生产完成：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
            saleListProductRepository.updateIussueState("生产完成：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
        } else {
            count += 1;
            saleListProductRepository.updateAccomplishNumberById(count, saleListProduct.getId());
        }

        BeanUtils.copyProperties(saleListProduct, storage);
        storage.setId(null);
        storage.setClerk(clerk);
        storage.setGroup(group);
        storage.setRealityweight(realityweight);
        storage.setDateInProduced(new Date(System.currentTimeMillis()));
        storage.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
        storageRepository.save(storage);

//
//        jitaiProductionAllotRepository.updateStateById("生产完成：" + jiTai.getName(), jitaiProductionAllotId);
//
//        Storage storage = new Storage();
//
//        storage.setAccomplishState("完成");
//        storage.setAllorTime(jitaiProductionAllot.getAllorTime());
//        storage.setAllotState(jitaiProductionAllot.getAllotState());
//        storage.setBrand(saleListProduct.getBrand());
//        storage.setClientname(saleListProduct.getClientname());
//        storage.setClerk(clerk);
//        storage.setColor(saleListProduct.getColor());
//        storage.setDao(saleListProduct.getDao());
//        storage.setDemand(saleListProduct.getDemand());
//        storage.setInformNumber(jitaiProductionAllot.getInformNumber());
//        storage.setIssueState(jitaiProductionAllot.getIssueState());
//        storage.setJiTai(jiTai);
//        storage.setJitaiProductionAllot(jitaiProductionAllot);
//        storage.setLabel(saleListProduct.getLabel());
//        storage.setLength(saleListProduct.getLength());
//        storage.setLetter(saleListProduct.getLetter());
//        storage.setMeter(saleListProduct.getMeter());
//        storage.setModel(saleListProduct.getModel());
//        storage.setName(saleListProduct.getName());
//        storage.setNum(saleListProduct.getNum());
//        storage.setNumsquare(saleListProduct.getNumsquare());
//        storage.setOneweight(saleListProduct.getOneweight());
//        storage.setPack(saleListProduct.getPack());
//        storage.setPatu(saleListProduct.getPatu());
//        storage.setPeasant(saleListProduct.getPeasant());
//        storage.setPrice(saleListProduct.getPrice());
//        storage.setProductionMessage(jitaiProductionAllot.getProductionMessage());
//        storage.setRealitymodel(saleListProduct.getRealitymodel());
//        storage.setRealityprice(saleListProduct.getRealityprice());
//        storage.setRealityweight(weight);
//        storage.setSaleList(saleListProduct.getSaleList());
//        storage.setSaleListProduct(saleListProduct);
//        storage.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
//        storage.setSquare(saleListProduct.getSquare());
//        storage.setState("生产完成：" + saleListProduct.getJiTai().getName());
//        storage.setSumwight(saleListProduct.getSumwight());
//        storage.setTaskQuantity(jitaiProductionAllot.getTaskQuantity());
//        storage.setTheoryweight(saleListProduct.getTheoryweight());
//        storage.setWeight(saleListProduct.getWeight());
//        storage.setWeightway(saleListProduct.getWeightway());
//        storage.setWightset(saleListProduct.getWightset());
//        storage.setDateInProduced(new Date(System.currentTimeMillis()));
//        storage.setClerk(clerk);
//
//        storageRepository.save(storage);

    }

    @Override
    public List<Storage> fandAll() {
        return storageRepository.findAll();
    }

    @Override
    public List<Storage> fandAllBySerialNumber(String serialNumber) {
        return storageRepository.fandAllBySerialNumber(serialNumber);
    }

    @Override
    public Storage selectByMaxId() {
        return storageRepository.selectByMaxId();
    }

    @Override
    public void outStorage(int id, Date date) {
        // TODO Auto-generated method stub
        storageRepository.outStorage(id, date);
        saleListProductRepository.updateState("提货:" + storageRepository.findOne(id).getJiTai().getName(), storageRepository.findOne(id).getSaleListProduct().getId());
    }

    @Override
    public List<Storage> outSuccess() {
        // TODO Auto-generated method stub
        return storageRepository.outSuccess();
    }


    @Override
    public void gai(String storage_number, int id) {
        // TODO Auto-generated method stub
        storageRepository.gai(storage_number, id);
    }

    @Override
    public void updateStateById(String state, Integer id, Date date) {
        storageRepository.updateStateById(state, id, date);
    }

    @Override
    public List<Object[]> findByClientAndGroupByName(String client) {
        List<Object[]> byClientAndGroupByName = storageRepository.findByClientAndGroupByName(client);
        System.out.println("==============================");
        System.out.println(byClientAndGroupByName.size());
        System.out.println(byClientAndGroupByName);
        for (Object[] obj : byClientAndGroupByName) {
            for (int i = 0; i < obj.length; i++) {
                System.out.println(obj[i]);
            }
        }
        return byClientAndGroupByName;
    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Override
    public Storage findById(Integer s) {
        return storageRepository.findOne(s);
    }

    @Override
    public String getTodayMaxOutNumber() {
        // TODO Auto-generated method stub
        return storageRepository.getTodayMaxOutNumber();
    }

    @Override
    public List<JieSuan> FindByGroup(String client) {
        System.out.println(client);
        System.out.println(storageRepository.FindByGroup(client));
        List<JieSuan> castEntity = EntityUtils.castEntity(storageRepository.FindByGroup(client), JieSuan.class);
        return castEntity;
    }

    @Override
    public List<Storage> searchLiftMoney(Map<String, Object> map) {
        return storageRepository.findAll(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty((String) map.get("saleNumber"))) {
                    predicate.getExpressions().add(cb.like(root.get("saleNumber"), (String) map.get("saleNumber")));
                }
                if (map.get("location") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("location").get("id"), map.get("location")));
                }
                if (map.get("jitai") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), map.get("jitai")));
                }
                if (StringUtil.isNotEmpty((String) map.get("productDate"))) {
                    predicate.getExpressions().add(cb.like(root.get("dateInProduced"), "%" + map.get("productDate") + "%"));
                }
                if (map.get("clerk") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("clerk").get("id"), map.get("clerk")));
                }
                if (map.get("group") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("group").get("id"), map.get("group")));
                }
                if (StringUtil.isNotEmpty((String) map.get("peasant"))) {
                    predicate.getExpressions().add(cb.like(root.get("peasant"), "%" + map.get("peasant") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("state"))) {
                    predicate.getExpressions().add(cb.like(root.get("state"), "%" + map.get("state") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("name"))) {
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + map.get("name") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("client"))) {
                    predicate.getExpressions().add(cb.like(root.get("clientname"), "%" + map.get("client") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("mode"))) {
                    predicate.getExpressions().add(cb.like(root.get("model"), "%" + map.get("mode") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("price"))) {
                    predicate.getExpressions().add(cb.like(root.get("price"), "%" + map.get("price") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("color"))) {
                    predicate.getExpressions().add(cb.like(root.get("color"), "%" + map.get("color") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("address"))) {
                    predicate.getExpressions().add(cb.like(root.get("saleList").get("address"), "%" + map.get("address") + "%"));
                }
                return predicate;
            }
        });
    }

    @Override
    public void setLocation(Integer parseInt, Integer location) {
        storageRepository.setLocation(parseInt, location);
    }

    @Override
    public void save(Storage storage) {
        storageRepository.save(storage);
    }

    @Override
    public void save(Storage storage, Integer num) {
        if (num != null) {
            for (int i = 0; i < num; i++) {
                Storage storage1 = new Storage();
                storage1.setName(storage.getName());
                storage1.setModel(storage.getModel());
                storage1.setPrice(storage.getPrice());
                storage1.setLength(storage.getLength());
                storage1.setColor(storage.getColor());
                storage1.setWeight(storage.getWeight());
                storage1.setDao(storage.getDao());
                storage1.setBrand(storage.getBrand());
                storage1.setPack(storage.getPack());
                storage1.setLetter(storage.getLetter());
                storage1.setPatu(storage.getPatu());
                storage1.setMeter(storage.getMeter());
                storage1.setClientname(storage.getClientname());
                storage1.setState(storage.getState());
                storage1.setLocation(storage.getLocation());
                storageRepository.save(storage1);
            }
        }
    }

    @Override
    public List<Storage> findByState(String state) {
        return storageRepository.findByState(state);
    }

    @Override
    public List<Storage> detail(Map<String, Object> map) {
        if (map.get("order") != null && map.get("order") != "") {
            return storageRepository.findAll(new Specification<Storage>() {
                public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                    Predicate predicate = cb.conjunction();
                    if (map.get("date") != null) {
                        predicate.getExpressions().add(cb.equal(root.get("deliveryTime"), map.get("date")));
                    }
                    if (StringUtil.isNotEmpty((String) map.get("client"))) {
                        predicate.getExpressions().add(cb.like(root.get("clientname"), "%" + map.get("client") + "%"));
                    }
                    if (StringUtil.isNotEmpty((String) map.get("peasant"))) {
                        predicate.getExpressions().add(cb.like(root.get("peasant"), "%" + map.get("peasant") + "%"));
                    }
                    if (StringUtil.isNotEmpty((String) map.get("product"))) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + map.get("product") + "%"));
                    }

                    predicate.getExpressions().add(cb.like(root.get("state"), "%装车%"));

                    query.groupBy(root.get("saleListProduct").get("id"));

                    return predicate;
                }
            }, new Sort(Sort.Direction.ASC, (String) map.get("order")));
        }
        return storageRepository.findAll(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (map.get("date") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("deliveryTime"), map.get("date")));
                }
                if (StringUtil.isNotEmpty((String) map.get("client"))) {
                    predicate.getExpressions().add(cb.like(root.get("clientname"), "%" + map.get("client") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("peasant"))) {
                    predicate.getExpressions().add(cb.like(root.get("peasant"), "%" + map.get("peasant") + "%"));
                }
                if (StringUtil.isNotEmpty((String) map.get("product"))) {
                    predicate.getExpressions().add(cb.like(root.get("name"), "%" + map.get("product") + "%"));
                }

                predicate.getExpressions().add(cb.like(root.get("state"), "%装车%"));

                query.groupBy(root.get("saleListProduct").get("id"));
                return predicate;
            }
        });
    }

    @Override
    public void updateOutNumberById(Integer parseInt) throws Exception {
        storageRepository.updateOutNumberById(this.genCode(), parseInt);
    }

    @Override
    public List<Storage> selectClientNameByOutDate(Date s) {
        return storageRepository.selectClientNameByOutDate(s);
    }

    @Override
    public List<Storage> selectOutByOutNumber(String outNumber) {
        return storageRepository.selectOutByOutNumber(outNumber);
    }

    @Override
    public String selectCountByNameAndOutNumber(String name, String outNumber) {
        return storageRepository.selectCountByNameAndOutNumber(name, outNumber);
    }


    /**
     * @Description: 生成出库单号
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("CK");
        code.append(DateUtil.getCurrentDateStr());
        String saleNumber = this.getTodayMaxOutNumber();
        if (saleNumber != null) {
            code.append(StringUtil.formatCode(saleNumber));
        } else {
            code.append("00001");
        }
        return code.toString();
    }

    /***
     *
     * 根据
     * @param
     * @return
     */
    @Override
    public List<Count> FindBySaleListId() {
        List<Count> cast = EntityUtils.castEntity(storageRepository.FindBySaleListId(), Count.class);
        return cast;
    }

    @Override
    public Integer countBySaleListProductId(Integer id) {
        Long count = storageRepository.count(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (id != null && id != 0) {
                    predicates.add(cb.equal(root.get("saleListProduct").get("id"), id));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getRestriction();
            }
        });
        return count.intValue();
    }

}
