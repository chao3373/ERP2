package com.shenke.service.impl;

import com.shenke.entity.MaoJinShu;
import com.shenke.repository.MaoJinShuRepository;
import com.shenke.service.MaoJinShuService;
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

@Service("maoJinShuService")
public class MaoJinShuServiceImpl implements MaoJinShuService {
    @Resource
    private MaoJinShuRepository maoJinShuRepository;

    @Override
    public void save(MaoJinShu maoJinShu) {
        maoJinShuRepository.save(maoJinShu);
    }

    @Override
    public List<MaoJinShu> list(MaoJinShu maoJinShu, Integer page, Integer rows) {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<MaoJinShu> MaoJinShus = maoJinShuRepository.findAll(new Specification<MaoJinShu>() {
            @Override
            public Predicate toPredicate(Root<MaoJinShu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (maoJinShu != null) {
                    if (StringUtil.isNotEmpty(maoJinShu.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + maoJinShu.getName() + "%"));
                    }
                }
                return predicate;
            }
        }, pageable);
        return MaoJinShus.getContent();
    }

    @Override
    public Long getCount(MaoJinShu maoJinShu) {
        Long count = maoJinShuRepository.count(new Specification<MaoJinShu>() {
            @Override
            public Predicate toPredicate(Root<MaoJinShu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (maoJinShu != null) {
                    if (StringUtil.isNotEmpty(maoJinShu.getName())) {
                        predicate.getExpressions().add(cb.like(root.get("name"), "%" + maoJinShu.getName() + "%"));
                    }
                }
                return predicate;
            }
        });
        return count;
    }

    @Override
    public void delete(Integer id) {
        maoJinShuRepository.delete(id);
    }

    @Override
    public List<MaoJinShu> findByLikeName(String s) {
        return maoJinShuRepository.findByLikeName(s);
    }
}
