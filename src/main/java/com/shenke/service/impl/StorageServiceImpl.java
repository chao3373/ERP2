package com.shenke.service.impl;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        Clerk clerk = clerkRepository.findByNam(clerkName);
        Double realityweight = storage.getRealityweight();

        BeanUtils.copyProperties(saleListProduct, storage);
        storage.setId(null);
        storage.setClerk(clerk);
        storage.setGroup(group);
        storage.setRealityweight(realityweight);
        storage.setDateInProduced(new Date(System.currentTimeMillis()));
        storage.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
        storage.setState("生产完成:" + storage.getJiTai().getName());
        storage.setJiTaiName(storage.getJiTai().getName());
        storage.setClerkName(clerkName);
        storage.setGroup(storage.getJiTai().getGroup());
        storage.setGroupName(storage.getJiTai().getGroup().getName());
        storageRepository.save(storage);

    }

    @Override
    public void feibiaoAdd(Storage storage, String clerkName, String groupName) {

        Group group = groupRepository.findByGrouptName(groupName);
        SaleListProduct saleListProduct = saleListProductRepository.findOne(storage.getSaleListProduct().getId());
        Clerk clerk = clerkRepository.findByNam(clerkName);
        Double realityweight = storage.getRealityweight();

        BeanUtils.copyProperties(saleListProduct, storage);
        storage.setId(null);
        storage.setClerk(clerk);
        storage.setGroup(group);
        storage.setRealityweight(realityweight);
        storage.setDateInProduced(new Date(System.currentTimeMillis()));
        storage.setState("生产完成:" + storage.getJiTai().getName());
        storage.setJiTaiName(storage.getJiTai().getName());
        storage.setClerkName(clerkName);
        storage.setGroup(storage.getJiTai().getGroup());
        storage.setGroupName(storage.getJiTai().getGroup().getName());
        storage.setSaleListProduct(null);
        storageRepository.save(storage);

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
                    predicate.getExpressions().add(cb.equal(root.get("saleNumber"), (String) map.get("saleNumber")));
                }
                /*if (map.get("location") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("location").get("id"), map.get("location")));
                }
                if (map.get("jitai") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), map.get("jitai")));
                }*/
                if (StringUtil.isNotEmpty((String) map.get("productDate"))) {
                    try {
                        predicate.getExpressions().add(cb.equal(root.get("dateInProduced"), new SimpleDateFormat("yyy-MM-dd").parse((String) map.get("productDate"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                /*
                if (map.get("clerk") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("clerk").get("id"), map.get("clerk")));
                }
                if (map.get("group") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("group").get("id"), map.get("group")));
                }
                */
                if (StringUtil.isNotEmpty((String) map.get("peasant"))) {
                    predicate.getExpressions().add(cb.equal(root.get("peasant"), map.get("peasant")));
                }
                if (StringUtil.isNotEmpty((String) map.get("realityweight"))) {
                    predicate.getExpressions().add(cb.equal(root.get("realityweight"), map.get("realityweight")));
                }
                if (StringUtil.isNotEmpty((String) map.get("name"))) {
                    predicate.getExpressions().add(cb.equal(root.get("name"), map.get("name")));
                }
                if (StringUtil.isNotEmpty((String) map.get("client"))) {
                    predicate.getExpressions().add(cb.equal(root.get("clientname"), map.get("client")));
                }
                if (StringUtil.isNotEmpty((String) map.get("mode"))) {
                    predicate.getExpressions().add(cb.equal(root.get("model"), map.get("mode")));
                }
                if (StringUtil.isNotEmpty((String) map.get("price"))) {
                    predicate.getExpressions().add(cb.equal(root.get("price"), map.get("price")));
                }
                Subquery subQuery = query.subquery(String.class);

                Root from = subQuery.from(Storage.class);
                subQuery.select(from.get("id")).where(cb.like(from.get("state"), "%生产完成%"));
//                Predicate state1 = cb.like(from.get("state"), "%装车%");
//                Predicate state = cb.like(from.get("state"), "%提货%");
//                Predicate or = cb.or(state1);

                predicate.getExpressions().add(cb.or(root.get("id").in(subQuery)));

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

    public List<Storage> findSaleListNumber() {
        return storageRepository.findSaleListNumber();
    }

    @Override
    public List<Storage> findStorage(String saleNumber) {
        return storageRepository.findStorage(saleNumber);
    }


    @Override
    public List<Storage> KucunSearch(Map<String, Object> map) {
        return storageRepository.findAll(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty((String) map.get("saleDate"))) {
                    try {
                        predicate.getExpressions().add(cb.equal(root.get("saleList").get("saleDate"), new SimpleDateFormat("yyy-MM-dd").parse((String) map.get("saleDate"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (map.get("clientname") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("clientname"), map.get("clientname")));
                }
                if ((map.get("saleNumber") != null)) {
                    predicate.getExpressions().add(cb.equal(root.get("saleNumber"), map.get("saleNumber")));
                }

                return predicate;
            }
        });
    }


    @Override
    public List<Storage> JitaiProduct(Storage storage, java.util.Date date, java.util.Date star, java.util.Date end) {
        return storageRepository.findAll(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty(storage.getGroupName())) {
                    predicate.getExpressions().add(cb.equal(root.get("groupName"), storage.getGroupName()));
                }
                if (StringUtil.isNotEmpty(storage.getJiTaiName())) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTaiName"), storage.getJiTaiName()));
                }
                if (StringUtil.isNotEmpty(storage.getGroupName())){
                    predicate.getExpressions().add(cb.equal(root.get("groupName"), storage.getGroupName()));
                }
                if (star != null) {
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduced"), star));
                }
                if (end != null) {
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("dateInProduced"), end));
                }
                return predicate;
            }
        });
    }

    @Override
    public List<Storage> select(Storage storage, String dateInProducedd) {
        return storageRepository.findAll(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty(storage.getSaleNumber())) {
                    predicate.getExpressions().add(cb.equal(root.get("saleNumber"), storage.getSaleNumber()));
                }
                if (storage.getLocation() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("location").get("id"), storage.getLocation().getId()));
                }
                if (storage.getJiTai() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), storage.getJiTai().getId()));
                }
                if (StringUtil.isNotEmpty(storage.getPeasant())) {
                    predicate.getExpressions().add(cb.equal(root.get("peasant"), storage.getPeasant()));
                }
                if (StringUtil.isNotEmpty(dateInProducedd)) {
                    try {
                        predicate.getExpressions().add(cb.equal(root.get("dateInProduced"), new SimpleDateFormat("yyyy-MM-dd").parse(dateInProducedd)));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (storage.getGroup() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("group"), storage.getGroup()));
                }
                if (storage.getClerk() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("clerk").get("id"), storage.getClerk().getId()));
                }
                if (StringUtil.isNotEmpty(storage.getClientname())) {
                    predicate.getExpressions().add(cb.equal(root.get("clientname"), storage.getClientname()));
                }
                if (storage.getLength() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("length"), storage.getLength()));
                }
                if (storage.getModel() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("model"), storage.getModel()));
                }
                if (storage.getPrice() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("price"), storage.getPrice()));
                }
                if (storage.getRealityweight() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("realityweight"), storage.getRealityweight()));
                }
                predicate.getExpressions().add(cb.like(root.get("state"), "%生产完成%"));
                return predicate;
            }
        });
    }

    @Override
    public void updateByIdAndState(int parseInt, String state) {
        storageRepository.updateByIdAndState(parseInt, state);
    }

    @Override
    public List<Storage> selectByState(String state) {
        return storageRepository.selectByState(state);
    }

    @Override
    public void updateOutNumberById(int parseInt, String ck) {
        storageRepository.updateOutNumberById(ck, parseInt);
    }
}
