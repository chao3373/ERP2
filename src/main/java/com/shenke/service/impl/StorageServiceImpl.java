package com.shenke.service.impl;

import java.util.Date;
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
import com.shenke.service.ClientService;
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
    private ClientService clientService;

    @Resource
    private GroupRepository groupRepository;

    @Override
    public void add(Storage storage, String clerkName, String groupName) {
        System.out.println(storage);
        System.out.println();
        System.out.println(storage.getPrice());
        Group group = storage.getJiTai().getGroup();
        SaleListProduct saleListProduct = saleListProductRepository.findOne(storage.getSaleListProduct().getId());
        Clerk clerk = storage.getJiTai().getClerk();
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
        storage.setClerkName(clerk.getName());
        storage.setPrice(storage.getSaleListProduct().getPrice());
        storage.setGroup(storage.getJiTai().getGroup());
        storage.setGroupName(storage.getJiTai().getGroup().getName());
        System.out.println(storage);
        System.out.println(storage.getPrice());
        storageRepository.save(storage);

    }

    @Override
    public void add(Storage storage, String clerkName, String groupName, Double changdu) {

        System.out.println(storage);
        Group group = groupRepository.findByGrouptName(groupName);
        SaleListProduct saleListProduct = saleListProductRepository.findOne(storage.getSaleListProduct().getId());
        Clerk clerk = storage.getJiTai().getClerk();
        Double realityweight = storage.getRealityweight();

        BeanUtils.copyProperties(saleListProduct, storage);
        storage.setId(null);
        System.out.println(clerk);
        storage.setClerk(clerk);
        storage.setGroup(group);
        storage.setRealityweight(realityweight);
        storage.setDateInProduced(new Date(System.currentTimeMillis()));
        storage.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
        storage.setState("生产完成:" + storage.getJiTai().getName());
        storage.setLength(changdu);
        storage.setJiTaiName(storage.getJiTai().getName());
        storage.setClerkName(clerk.getName());
        storage.setGroup(storage.getJiTai().getGroup());
        storage.setGroupName(storage.getJiTai().getGroup().getName());
        storage.setPrice(storage.getPrice());
        System.out.println(storage);
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
    public List<Storage> fandAllBySerialNumber(String serialNumber, String state) {
        return storageRepository.fandAllBySerialNumber(serialNumber, state);
    }

    @Override
    public Storage selectByMaxId() {
        return storageRepository.selectByMaxId();
    }

    @Override
    public void outStorage(int id, Date date) {
        // TODO Auto-generated method stub
        storageRepository.outStorage(id, date);
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
    public void updateStateById(String state, Integer id, Date date, String ck) {
        storageRepository.updateStateById(state, id, date, ck);
    }

    @Override
    public List<Object[]> findByClientAndGroupByName(String client) {
        List<Object[]> byClientAndGroupByName = storageRepository.findByClientAndGroupByName(client);
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
                    if (StringUtil.isNotEmpty((String)map.get("date"))) {
                        String date = (String) map.get("date");
                        try {
                            String st = date + " 00:00:00";
                            String ed = date + " 23:59:59";
                            System.out.println(st);
                            System.out.println(ed);
                            Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(st);
                            Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ed);
                            System.out.println(star);
                            System.out.println(end);
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("deliveryTime"), star));
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("deliveryTime"), end));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
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

                    query.groupBy(root.get("saleListProduct").get("id"), root.get("name"), root.get("model"), root.get("price"), root.get("length"), root.get("color"), root.get("realityweight"), root.get("dao"), root.get("peasant"), root.get("clientname"));

                    return predicate;
                }
            }, new Sort(Sort.Direction.ASC, (String) map.get("order")));
        }
        return storageRepository.findAll(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty((String)map.get("date"))) {
                    String date = (String) map.get("date");
                    try {
                        String st = date + " 00:00:00";
                        String ed = date + " 23:59:59";
                        System.out.println(st);
                        System.out.println(ed);
                        Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(st);
                        Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(ed);
                        System.out.println(star);
                        System.out.println(end);
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("deliveryTime"), star));
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("deliveryTime"), end));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
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

                query.groupBy(root.get("saleListProduct").get("id"), root.get("name"), root.get("model"), root.get("price"), root.get("length"), root.get("color"), root.get("realityweight"), root.get("dao"), root.get("peasant"), root.get("clientname"));

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
    public Integer countBySaleListProductId(Integer id, Storage storage, String state) {
        System.out.println("开始查询数量");
        System.out.println(id);
        System.out.println(storage);
        System.out.println(state);
        Long count = storageRepository.count(new Specification<Storage>() {
            @Override
            public Predicate toPredicate(Root<Storage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicates = cb.conjunction();
                if (id != null && id != 0) {
                    predicates.getExpressions().add(cb.equal(root.get("saleListProduct").get("id"), id));
                    predicates.getExpressions().add(cb.equal(root.get("name"), storage.getName()));
                    predicates.getExpressions().add(cb.equal(root.get("model"), storage.getModel()));
                    predicates.getExpressions().add(cb.equal(root.get("price"), storage.getPrice()));
                    predicates.getExpressions().add(cb.equal(root.get("length"), storage.getLength()));
                    predicates.getExpressions().add(cb.equal(root.get("color"), storage.getColor()));
                    predicates.getExpressions().add(cb.equal(root.get("realityweight"), storage.getRealityweight()));
                    predicates.getExpressions().add(cb.equal(root.get("dao"), storage.getDao()));
                    predicates.getExpressions().add(cb.equal(root.get("peasant"), storage.getPeasant()));
                    predicates.getExpressions().add(cb.equal(root.get("clientname"), storage.getClientname()));
                    predicates.getExpressions().add(cb.like(root.get("state"), state));
                }
                query.groupBy(root.get("saleListProduct").get("id"), root.get("name"), root.get("model"), root.get("price"), root.get("length"), root.get("color"), root.get("realityweight"), root.get("dao"), root.get("peasant"), root.get("clientname"));
                return predicates;
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
                if (StringUtil.isNotEmpty(storage.getGroupName())) {
                    predicate.getExpressions().add(cb.equal(root.get("groupName"), storage.getGroupName()));
                }
                if (star != null) {
                    System.out.println("开始时间");
                    System.out.println(star);
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduced"), star));
                }
                if (end != null) {
                    System.out.println("结束时间");
                    System.out.println(end);
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
                    predicate.getExpressions().add(cb.like(root.get("saleNumber"), "%" + storage.getSaleNumber() + "%"));
                }
                if (StringUtil.isNotEmpty(storage.getName())){
                    predicate.getExpressions().add(cb.equal(root.get("name"), storage.getName()));
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
                if (StringUtil.isNotEmpty(dateInProducedd) && storage.getGroup() != null) {
                    if (StringUtil.isNotEmpty(dateInProducedd) && !storage.getGroupName().equals("夜班")) {
                        System.out.println("白班");
                        try {
                            java.util.Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 00:00:00");
                            java.util.Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 23:59:59");
                            System.out.println("开始时间：" + star);
                            System.out.println("结束时间：" + end);
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduced"), star));
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("dateInProduced"), end));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("夜班");
                        String starr = dateInProducedd + " 17:00:00";
                        String endd = dateInProducedd.split("-")[0] + "-" + dateInProducedd.split("-")[1] + "-" + (Integer.parseInt(dateInProducedd.split("-")[2]) + 1) + " 14:00:00";
                        try {
                            Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(starr);
                            Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endd);
                            System.out.println("开始时间：" + star);
                            System.out.println("结束时间：" + end);
                            predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduced"), star));
                            predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("dateInProduced"), end));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                    predicate.getExpressions().add(cb.equal(root.get("group"), storage.getGroup()));
                }

                if (StringUtil.isNotEmpty(dateInProducedd) && storage.getGroup() == null) {
                    try {
                        java.util.Date star = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 00:00:00");
                        java.util.Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateInProducedd + " 23:59:59");
                        System.out.println("开始时间：" + star);
                        System.out.println("结束时间：" + end);
                        predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("dateInProduced"), star));
                        predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("dateInProduced"), end));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (storage.getClerk() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("clerk").get("id"), storage.getClerk().getId()));
                }
                if (StringUtil.isNotEmpty(storage.getClientname())) {
                    predicate.getExpressions().add(cb.equal(root.get("clientname"), clientService.findById(Integer.parseInt(storage.getClientname())).getName()));
                }
                if (storage.getLength() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("length"), storage.getLength()));
                }
                if (storage.getModel() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("model"), storage.getModel()));
                }
                if (StringUtil.isNotEmpty(storage.getPrice())) {
                    predicate.getExpressions().add(cb.equal(root.get("price"), storage.getPrice()));
                }
                if (storage.getRealityweight() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("realityweight"), storage.getRealityweight()));
                }
                if (StringUtil.isNotEmpty(storage.getState())) {
                    String state = storage.getState();
                    System.out.println(storage.getState());
                    if (storage.getState().startsWith("'")) {
                        state = storage.getState().substring(1, storage.getState().length() - 1);
                        System.out.println(state);
                    }
                    predicate.getExpressions().add(cb.like(root.get("state"), "%" + state + "%"));
                }
                if (StringUtil.isNotEmpty(storage.getColor())){
                    predicate.getExpressions().add(cb.equal(root.get("color"), storage.getColor()));
                }
                predicate.getExpressions().add(cb.like(root.get("state"), "%生产完成%"));

                query.groupBy(root.get("saleListProduct").get("id"), root.get("name"), root.get("model"), root.get("price"), root.get("length"), root.get("color"), root.get("realityweight"), root.get("dao"), root.get("peasant"), root.get("clientname"));
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
        return storageRepository.selectByState("%" + state + "%");
    }

    @Override
    public void updateOutNumberById(int parseInt, String ck) {
        storageRepository.updateOutNumberById(ck, parseInt);
    }

    @Override
    public String genCode() throws Exception {
        StringBuffer code = new StringBuffer("CK");
        code.append(DateUtil.getCurrentDateStr());
        String saleNumber = storageRepository.getTodayMaxOutNumber();
        if (saleNumber != null) {
            code.append(StringUtil.formatCode(saleNumber));
        } else {
            code.append("00001");
        }
        return code.toString();
    }

    @Override
    public void updateClerk(Integer id, String clerkName, Integer clerkId) {
        storageRepository.updateClerk(id, clerkName, clerkId);
    }

    @Override
    public void editKuCun(Integer id, Integer oneWeight, Double shiji, Double length) {
        storageRepository.editKuCun(id, oneWeight, shiji, length);
    }

    @Override
    public List<Storage> findeBySaleNumberAndClient(String saleNumber, String client) {
        return storageRepository.findeBySaleNumberAndClient(saleNumber, client);
    }

    /***
     *
     * 按月查询报表
     * @param month
     * @param year
     * @return
     */
    @Override
    public Month selectMonth(String month, String year) {
        Month month1 = new Month();
        String m = Integer.parseInt(month) < 10 ? 0 + month : month;
        Integer month2 = Integer.parseInt(month) + 1;
        String m1 = month2 < 10 ? 0 + month2 + "" : month2 + "";
        String date = year + "-" + m + "-01";
        String date1 = year + "-" + m1 + "-01";
        try {
            java.util.Date udate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
            java.util.Date endate = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
            System.out.println(date);
            System.out.println(date1);
            Integer lastMonth = storageRepository.lastMonth(udate);
            Integer monthIn = storageRepository.monthIn(udate, endate);
            Integer monthOut = storageRepository.monthOut(udate, endate);
            Integer kuCun = lastMonth + monthIn - monthOut;
            month1.setLastMonth(lastMonth);
            month1.setMonthIn(monthIn);
            month1.setMonthOut(monthOut);
            month1.setKuCun(kuCun);
            return month1;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Month selectYear(String year) {
        Integer lastYear = Integer.parseInt(year) + 1;
        Month month = new Month();
        try {
            java.util.Date n = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-01-01");
            java.util.Date n1 = new SimpleDateFormat("yyyy-MM-dd").parse(lastYear + "-01-01");
            System.out.println(n);
            System.out.println(n1);
            Integer lastMonth = storageRepository.lastMonth(n);
            Integer monthIn = storageRepository.monthIn(n, n1);
            Integer monthOut = storageRepository.monthOut(n, n1);
            Integer kuCun = lastMonth + monthIn - monthOut;
            month.setLastMonth(lastMonth);
            month.setMonthIn(monthIn);
            month.setMonthOut(monthOut);
            month.setKuCun(kuCun);
            return month;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updatebanzu(Integer id, String banzu, Integer banzuid) {
        storageRepository.updatebanzu(id, banzu, banzuid);
    }

    /***
     * 根据id修改重量
     * @param id
     * @param zhongliang
     */
    @Override
    public void updatezhongliang(Integer id, Double zhongliang) {
        storageRepository.updatezhongliang(id, zhongliang);
    }

    /***
     * 根据id删除库存
     * @param id
     */
    @Override
    public String deletekucun(Integer id) {
        Storage storage = storageRepository.findOne(id);
        Integer saleListProductId = storage.getSaleListProduct().getId();
        SaleListProduct saleListProduct = saleListProductRepository.findOne(saleListProductId);
        storageRepository.deletekucun(id);
        //更新完成数量
        saleListProduct.setAccomplishNumber(saleListProduct.getAccomplishNumber() - 1);
//        saleListProductRepository.updateAccomplishNumber(saleListProductId);
        //根据id查询完成数跟总数量，完成数小于总数量则修改状态为下发机台
        if (saleListProduct.getNum() > saleListProduct.getAccomplishNumber()){
            saleListProduct.setState("下发机台：" + saleListProduct.getJiTai().getName());
            saleListProductRepository.save(saleListProduct);
            return "修改成功，并修改状态为下发！";
        } else {
            saleListProductRepository.save(saleListProduct);
            return "修改成功完成数量不小于总数量不修改状态！";
        }
    }

    @Override
    public void updatechangdu(Integer changdu, Integer id) {
        storageRepository.updatechangdu(changdu, id);
    }

    /***
     * 根据id修改厚度
     * @param houdu
     * @param id
     */
    @Override
    public void updatehoudu(String houdu, Integer id) {
        storageRepository.updatehoudu(houdu, id);
    }

    //根据条件查询提货商品
    @Override
    public List<Storage> selectTihuo(String pandianji) {
        if (StringUtil.isEmpty(pandianji)){
            return storageRepository.selectTihuo();
        }
        return storageRepository.selectTihuo(pandianji);
    }

    /***
     * 根据salelistproductid查询完成数量
     * @param id
     * @return
     */
    @Override
    public Integer findCountBySaleListProductId(Integer id) {
        return storageRepository.findCountBySaleListProductId(id);
    }

}
