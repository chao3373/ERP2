package com.shenke.service.impl;

import com.shenke.entity.QiTa;
import com.shenke.repository.QiTaRepository;
import com.shenke.service.QiTaService;
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

@Service("qiTaService")
public class QiTaServiceImpl implements QiTaService {
    @Resource
    private QiTaRepository qiTaRepository;

    @Override
    public void save(QiTa qiTa) {
        qiTaRepository.save(qiTa);
    }

    @Override
    public List<QiTa> list(QiTa qiTa, Integer page, Integer rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<QiTa> muLiaos = qiTaRepository.findAll(new Specification<QiTa>() {
            @Override
            public Predicate toPredicate(Root<QiTa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (qiTa != null) {
                    if (StringUtil.isNotEmpty(qiTa.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + qiTa.getName() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return muLiaos.getContent();
    }

    @Override
    public Long getCount(QiTa qiTa) {
        Long count = qiTaRepository.count(new Specification<QiTa>() {
            @Override
            public Predicate toPredicate(Root<QiTa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (qiTa != null) {
                    if (StringUtil.isNotEmpty(qiTa.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + qiTa.getName() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void delete(Integer id) {
        qiTaRepository.delete(id);
    }

    @Override
    public List<QiTa> findByLikeName(String s) {
        return qiTaRepository.findByLikeName(s);
    }
}
