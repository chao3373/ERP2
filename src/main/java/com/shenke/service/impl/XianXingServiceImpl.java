package com.shenke.service.impl;

import com.shenke.entity.XianXing;
import com.shenke.repository.XianXingRepository;
import com.shenke.service.XianXingService;
import com.shenke.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("xianXingService")
public class XianXingServiceImpl implements XianXingService {
    @Resource
    private XianXingRepository xianXingRepository;

    @Override
    public void save(XianXing xianXing) {
        xianXingRepository.save(xianXing);
    }

    @Override
    public List<XianXing> list(XianXing xianXing, Integer page, Integer rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<XianXing> muLiaos = xianXingRepository.findAll(new Specification<XianXing>() {
            @Override
            public Predicate toPredicate(Root<XianXing> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (xianXing != null) {
                    if (StringUtil.isNotEmpty(xianXing.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + xianXing.getName() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return muLiaos.getContent();
    }

    @Override
    public Long getCount(XianXing muLiao) {
        Long count = xianXingRepository.count(new Specification<XianXing>() {
            @Override
            public Predicate toPredicate(Root<XianXing> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (muLiao != null) {
                    if (StringUtil.isNotEmpty(muLiao.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + muLiao.getName() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void delete(Integer id) {
        xianXingRepository.delete(id);
    }

    @Override
    public List<XianXing> findByLikeName(String s) {
        return xianXingRepository.findByLikeName(s);
    }
}
