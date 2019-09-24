package com.shenke.service.impl;

import com.shenke.entity.SeMu;
import com.shenke.repository.SeMuRepository;
import com.shenke.service.SeMuService;
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

@Service("seMuService")
public class SeMuServiceImpl implements SeMuService {

    @Resource
    private SeMuRepository seMuRepository;

    @Override
    public void save(SeMu seMu) {
        seMuRepository.save(seMu);
    }

    @Override
    public List<SeMu> list(SeMu seMu, Integer page, Integer rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<SeMu> muLiaos = seMuRepository.findAll(new Specification<SeMu>() {
            @Override
            public Predicate toPredicate(Root<SeMu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (seMu != null) {
                    if (StringUtil.isNotEmpty(seMu.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + seMu.getName() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return muLiaos.getContent();
    }

    @Override
    public Long getCount(SeMu seMu) {
        Long count = seMuRepository.count(new Specification<SeMu>() {
            @Override
            public Predicate toPredicate(Root<SeMu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (seMu != null) {
                    if (StringUtil.isNotEmpty(seMu.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + seMu.getName() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void delete(Integer id) {
        seMuRepository.delete(id);
    }

    @Override
    public List<SeMu> findByLikeName(String s) {
        return seMuRepository.findByLikeName(s);
    }
}
