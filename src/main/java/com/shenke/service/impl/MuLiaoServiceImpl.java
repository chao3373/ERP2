package com.shenke.service.impl;

import com.shenke.entity.MuLiao;
import com.shenke.repository.MuLiaoRepository;
import com.shenke.service.MuLiaoService;
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

@Service("muLiaoService")
public class MuLiaoServiceImpl implements MuLiaoService {

    @Resource
    private MuLiaoRepository muLiaoRepository;

    @Override
    public void save(MuLiao muLiao) {
        muLiaoRepository.save(muLiao);
    }

    @Override
    public List<MuLiao> list(MuLiao muLiao, Integer page, Integer rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<MuLiao> muLiaos = muLiaoRepository.findAll(new Specification<MuLiao>() {
            @Override
            public Predicate toPredicate(Root<MuLiao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (muLiao != null) {
                    if (StringUtil.isNotEmpty(muLiao.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + muLiao.getName() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return muLiaos.getContent();
    }

    @Override
    public Long getCount(MuLiao muLiao) {
        Long count = muLiaoRepository.count(new Specification<MuLiao>() {
            @Override
            public Predicate toPredicate(Root<MuLiao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        muLiaoRepository.delete(id);
    }

    @Override
    public List<MuLiao> findByLikeName(String s) {
        return muLiaoRepository.findByLikeName(s);
    }
}
