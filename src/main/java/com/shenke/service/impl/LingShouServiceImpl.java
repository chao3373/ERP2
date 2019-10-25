package com.shenke.service.impl;

import com.shenke.entity.LingShou;
import com.shenke.entity.Storage;
import com.shenke.repository.LingShouRepository;
import com.shenke.repository.StorageRepository;
import com.shenke.service.LingShouService;
import com.shenke.service.StorageService;
import com.shenke.util.StringUtil;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("lingShouService")
@Transactional
public class LingShouServiceImpl implements LingShouService {

    @Resource
    private LingShouRepository lingShouRepository;

    @Resource
    private StorageRepository storageRepository;

    /***
     * 查询零售单号
     * @return
     */
    @Override
    public String getTodayMaxSaleNumber() {
        return lingShouRepository.getTodayMaxSaleNumber();
    }

    @Override
    public List<Storage> lingshouList(String s) {
        return storageRepository.lingshouList(s);
    }

    @Override
    public void save(List<LingShou> lingShous) {
        lingShouRepository.save(lingShous);
    }

    @Override
    public List<LingShou> findbydanhao(String danhao) {
        return lingShouRepository.findbydanhao(danhao);
    }

    @Override
    public LingShou findone(Integer id) {
        return lingShouRepository.findOne(id);
    }

    @Override
    public void saveone(LingShou findone) {
        lingShouRepository.save(findone);
    }

    @Override
    public List<LingShou> find(LingShou lingShou) {
        return lingShouRepository.findAll(new Specification<LingShou>() {
            @Override
            public Predicate toPredicate(Root<LingShou> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (lingShou.getLength() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("length"), lingShou.getLength()));
                }
                if (lingShou.getModel() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("model"), lingShou.getModel()));
                }
                if (lingShou.getPrice() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("price"), lingShou.getPrice()));
                }
                if (lingShou.getWeight() != null) {
                    predicate.getExpressions().add(cb.equal(root.get("weight"), lingShou.getWeight()));
                }
                if (StringUtil.isNotEmpty(lingShou.getName())) {
                    predicate.getExpressions().add(cb.equal(root.get("name"), lingShou.getName()));
                }
                if (StringUtil.isNotEmpty(lingShou.getPeasant())) {
                    predicate.getExpressions().add(cb.equal(root.get("name"), lingShou.getPeasant()));
                }
                if (StringUtil.isNotEmpty(lingShou.getClientname())) {
                    predicate.getExpressions().add(cb.equal(root.get("clientname"), lingShou.getClientname()));
                }
                if (lingShou.getStarDate() != null) {
                    predicate.getExpressions().add(cb.greaterThanOrEqualTo(root.get("xiaoshouDate"), lingShou.getStarDate()));
                }
                if (lingShou.getEndDate() != null) {
                    predicate.getExpressions().add(cb.lessThanOrEqualTo(root.get("xiaoshouDate"), lingShou.getEndDate()));
                }
                if (StringUtil.isNotEmpty(lingShou.getDanhao())){
                    predicate.getExpressions().add(cb.equal(root.get("danhao"), lingShou.getDanhao()));
                }
                return predicate;
            }
        });
    }

    @Override
    public void updateShishou(Integer id, Double shishou) {
        lingShouRepository.updateShishou(id, shishou);
    }
}
