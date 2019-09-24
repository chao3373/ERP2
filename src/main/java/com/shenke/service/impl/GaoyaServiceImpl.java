package com.shenke.service.impl;

import com.shenke.entity.GaoYa;
import com.shenke.repository.GaoyaRepository;
import com.shenke.service.GaoyaService;
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

@Service("gaoYaService")
public class GaoyaServiceImpl implements GaoyaService {

    @Resource
    private GaoyaRepository gaoyaRepository;
    @Override
    public void save(GaoYa gaoYa) {
        gaoyaRepository.save(gaoYa);
    }

    @Override
    public List<GaoYa> list(GaoYa GaoYa, Integer page, Integer rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<com.shenke.entity.GaoYa> muLiaos = gaoyaRepository.findAll(new Specification<com.shenke.entity.GaoYa>() {
            @Override
            public Predicate toPredicate(Root<com.shenke.entity.GaoYa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (GaoYa != null) {
                    if (StringUtil.isNotEmpty(GaoYa.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + GaoYa.getName() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return muLiaos.getContent();
    }

    @Override
    public Long getCount(GaoYa muLiao) {
        Long count = gaoyaRepository.count(new Specification<GaoYa>() {
            @Override
            public Predicate toPredicate(Root<GaoYa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        gaoyaRepository.delete(id);
    }

    @Override
    public List<GaoYa> findByLikeName(String s) {
        return gaoyaRepository.findByLikeName(s);
    }
}
