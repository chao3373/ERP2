package com.shenke.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import com.shenke.entity.Log;
import com.shenke.util.LogUtil;
import com.shenke.util.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.Client;
import com.shenke.entity.SaleList;
import com.shenke.entity.SaleListProduct;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.SaleListProductService;

@Service("saleListProductService")
public class SaleListProductServiceImpl implements SaleListProductService {

    @Resource
    private SaleListProductRepository saleListProductRepository;

    @Override
    public List<SaleListProduct> listBySaleListId(Integer saleListId) {
        return saleListProductRepository.listBySaleListId(saleListId);
    }

    @Override
    public void deleteBySaleListId(Integer id) {
        saleListProductRepository.deleteBySaleListId(id);
    }

    @Override
    public void passTheAudit(int id) {
        saleListProductRepository.passTheAudit(id);
    }

    @Override
    public void auditFailure(int id, String cause) {
        saleListProductRepository.auditFailure(id, cause);
    }

    @Override
    public List<SaleListProduct> fandAll() {
        return saleListProductRepository.findAll();
    }

    public List<SaleListProduct> fandAll(Iterable<Integer> ids) {
        return saleListProductRepository.findAll(ids);
    }

    @Override
    public List<SaleListProduct> listProductSucceed() {
        return saleListProductRepository.listProductSucceed();
    }

    @Override
    public List<SaleListProduct> breadthList(String q) {
        return saleListProductRepository.breadthList(q);
    }

    @Override
    public List<SaleListProduct> screen(Map<String, Object> condition) {


        List<Order> orders = new ArrayList<Order>();

        Specification<SaleListProduct> specification = new Specification<SaleListProduct>() {
            Predicate predicate = null;

            @Override
            public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                predicate = cb.conjunction();
                predicate.getExpressions().add(cb.like(root.get("state"), "%审核通过%"));
                if (condition.get("client") != null) {

                    Subquery<Integer> subquery2 = query.subquery(Integer.class);
                    Root<Client> fromC = subquery2.from(Client.class);
                    subquery2.select(fromC.get("id")).where(cb.equal(fromC.get("name"), condition.get("client")));

                    Subquery<Integer> subquery = query.subquery(Integer.class);
                    Root<SaleList> fromSL = subquery.from(SaleList.class);
                    subquery.select(fromSL.get("id")).where(fromSL.get("client").get("id").in(subquery2));

                    predicate.getExpressions().add(cb.and(root.get("saleList").get("id").in(subquery)));
                }

                if (condition.get("modeSort") != null) {
                    predicate.getExpressions().add(cb.and(cb.equal(root.get("model"), condition.get("modeSort"))));
                }
                if (condition.get("priceSort") != null) {
                    predicate.getExpressions().add(cb.and(cb.equal(root.get("price"), condition.get("priceSort"))));
                }
                if (condition.get("lengthSort") != null) {
                    predicate.getExpressions().add(cb.and(cb.equal(root.get("length"), condition.get("lengthSort"))));
                }
                if (condition.get("realityprice") != null) {
                    predicate.getExpressions().add(cb.and(cb.equal(root.get("realityprice"), condition.get("realityprice"))));
                }
                if (condition.get("oneweight") != null) {
                    predicate.getExpressions().add(cb.and(cb.equal(root.get("oneweight"), condition.get("oneweight"))));
                }
                if (condition.get("sumwight") != null) {
                    predicate.getExpressions().add(cb.and(cb.equal(root.get("sumwight"), condition.get("sumwight"))));
                }
                if (condition.get("realitymodel") != null) {
                    predicate.getExpressions()
                            .add(cb.and(cb.equal(root.get("realitymodel"), condition.get("realitymodel"))));
                }

                return predicate;
            }
        };

        return saleListProductRepository.findAll(specification);

    }

    @Override
    public void updateState(String name, Integer id) {
        saleListProductRepository.updateState(name, id);
    }

    @Override
    public void saveList(List<SaleListProduct> plgList) {
        saleListProductRepository.save(plgList);
//        for (SaleListProduct saleListProduct : plgList) {
//            saleListProduct.setDaBaoShu(1);
//            saleListProductRepository.save(saleListProduct);
//        }
    }

    @Override
    public void updateJitaiId(Integer id, Integer id1) {
        saleListProductRepository.updateJitaiId(id, id1);
    }

    @Override
    public List<SaleListProduct> selectByJitaiIdAndIssueStateAndInformNumber(Integer jitaiId, String state, Long infLong) {
        System.out.println(state);
        return saleListProductRepository.findAll(new Specification<SaleListProduct>() {
            @Override
            public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (jitaiId != null) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), jitaiId));
                }
                if (StringUtil.isNotEmpty(state)) {
                    predicate.getExpressions().add(cb.like(root.get("state"), "%" + state + "%"));
                }
                if (infLong != null) {
                    predicate.getExpressions().add(cb.equal(root.get("informNumber"), infLong));
                }
                return predicate;
            }
        }, new Sort(Sort.Direction.DESC, "level"));
    }

    @Override
    public void updateInformNumber(Long informNumber, int id) {
        saleListProductRepository.updateInformNumber(informNumber, id);
    }

    @Override
    public void updateIussueState(String issueState, int id) {
        saleListProductRepository.updateIussueState(issueState, id);
    }

    @Override
    public List<SaleListProduct> selectNoAccomplish(Integer jitaiId) {
        return saleListProductRepository.selectNoAccomplish(jitaiId);
    }

    @Override
    public List<SaleListProduct> listProductByState(String state) {
        return saleListProductRepository.listProductByState(state);
    }

    @Override
    public Long findMaxInfornNumber() {
        return saleListProductRepository.findMaxInfornNumber();
    }

    @Override
    public void save(SaleListProduct saleListProduct) {
        saleListProductRepository.save(saleListProduct);
    }

    @Override
    public SaleListProduct findById(int parseInt) {
        return saleListProductRepository.findOne(parseInt);
    }

    @Override
    public List<SaleListProduct> findJitaiProduct() {
        return saleListProductRepository.findJitaiProduct();
    }


    @Override
    public List<SaleListProduct> searchJitai(Map<String, Object> map) {

        System.out.println(map);

        return saleListProductRepository.findAll(new Specification<SaleListProduct>() {
            @Override
            public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty((String) map.get("saleDate"))) {
                    try {
                        predicate.getExpressions().add(cb.equal(root.get("saleList").get("saleDate"), new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("saleDate"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (map.get("jitai") != null) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), map.get("jitai")));
                }
                if (StringUtil.isNotEmpty((String) map.get("deliveryDate"))) {
                    try {
                        predicate.getExpressions().add(cb.equal(root.get("saleList").get("deliveryDate"), new SimpleDateFormat("yyyy-MM-dd").parse((String) map.get("deliveryDate"))));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                if (StringUtil.isNotEmpty((String) map.get("saleNumber"))) {
                    predicate.getExpressions().add(cb.equal(root.get("saleList").get("saleNumber"), map.get("saleNumber")));
                }
                if (map.get("allorState") != null) {
                    //predicate.getExpressions().add(cb.like(root.get("saleNumber"), (String) map.get("saleNumber")));
                    predicate.getExpressions().add(cb.like(root.get("issueState"), "%" + map.get("allorState") + "%"));
                }
                if (map.get("state") != null) {
                    predicate.getExpressions().add(cb.like(root.get("state"), "%" + map.get("state") + "%"));
                }
                return predicate;

            }
        });
    }

//    @Override
//    public String updateAccomplishNumber(Integer id) {
//        SaleListProduct saleListProduct = this.findById(id);
//        //完成数+1
//        Integer count = saleListProduct.getAccomplishNumber() == null ? 0 : saleListProduct.getAccomplishNumber();
//        System.out.println("count: " + count);
//        Integer num = saleListProduct.getNum();
//        Integer daBaoShu = saleListProduct.getDaBaoShu();
//        Integer countt = count + 1;
//        System.out.println("countt:" + countt);
//        saleListProductRepository.updateAccomplishNumberById(countt, saleListProduct.getId());
//        if (countt == num) {
//            saleListProductRepository.updateState("生产完成：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
//            if (countt % daBaoShu == 0) {
//                return "生产完成:" + daBaoShu;
//            } else {
//                return "生产完成:" + countt % daBaoShu;
//            }
//        } else if (countt % daBaoShu == 0) {
//            return "打包完成:" + daBaoShu;
//        } else {
//            return "只增加数量";
//        }
//    }

    @Override
    public String updateAccomplishNumber(Integer id) {
        SaleListProduct saleListProduct = this.findById(id);
        //完成数+1
        Integer count = saleListProduct.getAccomplishNumber() == null ? 0 : saleListProduct.getAccomplishNumber();
        Integer num = saleListProduct.getNum();
        Integer daBaoShu = saleListProduct.getDaBaoShu();
        Integer shengyu = num - count;
        Integer countt = count + daBaoShu;
        LogUtil.printLog("当前完成数量：" + count);
        LogUtil.printLog("剩余数量：" + shengyu);
        LogUtil.printLog("当前设置打包数量：" + daBaoShu);
        LogUtil.printLog("当前完成数加打包数量：" + countt);
        if (countt > num) {
            LogUtil.printLog("当前完成数加打包数量大于总数量--生产完成");
            saleListProductRepository.updateAccomplishNumberById(num, saleListProduct.getId());
            saleListProductRepository.updateState("生产完成：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
            LogUtil.printLog("生产完成---" + "打包数量：" + (num - count) + "机台名称：" + saleListProduct.getJiTai().getName() + "客户名：" + saleListProduct.getClientname() + "长度：" + saleListProduct.getLength() + "幅宽：" + saleListProduct.getModel() + "厚度：" + saleListProduct.getPrice());
            return "生产完成:" + (num - count);
        } else if (countt < num) {
            LogUtil.printLog("当前完成数加打包数量小于总数量--打包完成");
            saleListProductRepository.updateAccomplishNumberById(countt, saleListProduct.getId());
            LogUtil.printLog("打包完成---" + "打包数量：" + daBaoShu + "机台名称：" + saleListProduct.getJiTai().getName() + "客户名：" + saleListProduct.getClientname() + "长度：" + saleListProduct.getLength() + "幅宽：" + saleListProduct.getModel() + "厚度：" + saleListProduct.getPrice());
            return "打包完成:" + daBaoShu;
        } else {
            LogUtil.printLog("当前完成数加打包数量等于总数量--生产完成");
            saleListProductRepository.updateAccomplishNumberById(num, saleListProduct.getId());
            saleListProductRepository.updateState("生产完成：" + saleListProduct.getJiTai().getName(), saleListProduct.getId());
            LogUtil.printLog("生产完成---" + "打包数量：" + daBaoShu + "机台名称：" + saleListProduct.getJiTai().getName() + "客户名：" + saleListProduct.getClientname() + "长度：" + saleListProduct.getLength() + "幅宽：" + saleListProduct.getModel() + "厚度：" + saleListProduct.getPrice());
            return "生产完成:" + daBaoShu;
        }
    }

    @Override
    public void hebingJian(Integer count, Integer id) {
        SaleListProduct saleListProduct = saleListProductRepository.findOne(id);
        SaleListProduct saleListProduct1 = new SaleListProduct();
        BeanUtils.copyProperties(saleListProduct, saleListProduct1);
        saleListProduct1.setId(null);
        Integer num = saleListProduct.getNum();
        saleListProduct.setNum(num / count);
        Double oneweight = saleListProduct.getOneweight();
        Double sumOneWeight = oneweight;
        double lengthh = saleListProduct.getLength();
        int length = (int) lengthh;
        StringBuilder sb = new StringBuilder();
        sb.append(length + "");
        Integer countNum = length;
        for (int i = 0; i < count - 1; i++) {
            sumOneWeight += oneweight;
            sb.append("+" + length);
            countNum += length;
        }
        saleListProduct.setHebingLength(sb.toString());
        saleListProduct.setLength(Double.parseDouble(countNum.toString()));
        saleListProduct.setOneweight(sumOneWeight);
        saleListProductRepository.save(saleListProduct);
        if (num % count != 0) {
            StringBuilder leng = new StringBuilder();
            leng.append(length + "");
            Integer countNum2 = length;
            Double sumOneWeight1 = 0.0;
            for (int i = 0; i < num % count - 1; i++) {
                sumOneWeight1 += oneweight;
                leng.append("+" + leng);
                countNum2 += num;
            }
            saleListProduct1.setHebingLength(leng.toString());
            saleListProduct1.setNum(1);
            saleListProduct1.setLength(Double.parseDouble(countNum2.toString()));
            saleListProduct1.setOneweight(sumOneWeight1);
            saleListProductRepository.save(saleListProduct1);
        }
    }

    @Override
    public void deleteById(int parseInt) {
        saleListProductRepository.delete(parseInt);
    }

    @Override
    public void dingdanjiaji(Integer id, String jiajidengji) {

        saleListProductRepository.dingdanjiaji(id, jiajidengji);
    }

    @Override
    public void chanpinjiaji(Integer id, String jiajidengji) {
        saleListProductRepository.chanpinjiaji(id, jiajidengji);
    }

    @Override
    public List<SaleListProduct> condition(SaleListProduct saleListProduct) {
        return saleListProductRepository.findAll(new Specification<SaleListProduct>() {
            @Override
            public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtil.isNotEmpty(saleListProduct.getName())) {
                    predicate.getExpressions().add(cb.equal(root.get("name"), saleListProduct.getName()));
                }
                if (StringUtil.isNotEmpty(saleListProduct.getClientname())) {
                    predicate.getExpressions().add(cb.equal(root.get("clientname"), saleListProduct.getClientname()));
                }
                if (saleListProduct.getMeter() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("price"), saleListProduct.getMeter()));
                }
                if (saleListProduct.getModel() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("model"), saleListProduct.getModel()));
                }
                if (StringUtil.isNotEmpty(saleListProduct.getColor())) {
                    predicate.getExpressions().add(cb.equal(root.get("color"), saleListProduct.getColor()));
                }
                if (StringUtil.isNotEmpty(saleListProduct.getDao())) {
                    predicate.getExpressions().add(cb.equal(root.get("dao"), saleListProduct.getDao()));
                }
                predicate.getExpressions().add(cb.equal(root.get("jiTai"), saleListProduct.getJiTai()));
                predicate.getExpressions().add(cb.like(root.get("state"), "%下发机台%"));
                return predicate;
            }
        });
    }

    @Override
    public List<SaleListProduct> findByJitaiId(SaleListProduct saleListProduct) {
        return saleListProductRepository.findAll(new Specification<SaleListProduct>() {
            @Override
            public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (saleListProduct.getJiTai() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), saleListProduct.getJiTai().getId()));
                }
                predicate.getExpressions().add(cb.like(root.get("issueState"), "%" + saleListProduct.getIssueState() + "%"));
                return predicate;
            }
        });
    }

    /***
     * 修改数量
     * @param num
     * @return
     */
    @Override
    public String updateNum(Integer num, Integer id) {
        SaleListProduct saleListProduct = saleListProductRepository.findOne(id);
        Integer wc = saleListProduct.getAccomplishNumber();
        if (wc == null) {
            wc = 0;
        }
        if (wc == num) {
            saleListProductRepository.updateNum(num, id);
            saleListProductRepository.updateState("生产完成：" + saleListProduct.getJiTai().getName(), id);
            return "修改成功，数量等于完成数，修改订单状态为完成";
        } else if (wc > num) {
            return "已经完成比该数量多的件数，无法修改";
        } else {
            saleListProductRepository.updateNum(num, id);
            String state = saleListProductRepository.findOne(id).getState();
            if (state.startsWith("审核成功") || state.startsWith("未审核")) {
                return "修改成功！";
            }
            saleListProductRepository.updateState("下发机台：" + saleListProduct.getJiTai().getName(), id);
            return "修改成功，数量大于完成数，修改订单状态为下发机台";
        }
    }
}
