package com.shenke.service.impl;

import com.shenke.entity.PeiFangInfo;
import com.shenke.repository.PeiFangInfoRepository;
import com.shenke.service.PeiFangInfoService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service("peiFangInfoService")
public class PeiFangInfoServiceImpl implements PeiFangInfoService {

    @Resource
    private PeiFangInfoRepository peiFangInfoRepository;

    @Override
    public List<PeiFangInfo> list(PeiFangInfo peiFangInfo) {
        return peiFangInfoRepository.findAll(new Specification<PeiFangInfo>() {
            @Override
            public Predicate toPredicate(Root<PeiFangInfo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (peiFangInfo != null && peiFangInfo.getPeiFang() != null) {
                    if (peiFangInfo.getPeiFang().getId() != null) {
                        predicate.getExpressions().add(cb.equal(root.get("peiFang").get("id"), peiFangInfo.getPeiFang().getId()));
                    }
                }
                return predicate;
            }
        });
    }

    @Override
    public List<PeiFangInfo> findByPeiFangId(Integer id) {
        return peiFangInfoRepository.findByPeiFangId(id);
    }

    @Override
    public void delete(List<PeiFangInfo> peiFangInfos) {
        peiFangInfoRepository.delete(peiFangInfos);
    }

    @Override
    public void save(PeiFangInfo peiFangInfo) {
        peiFangInfoRepository.save(peiFangInfo);
    }

    @Override
    public PeiFangInfo findByCengAndPeiFangId(String ceng, Integer id) {
        return peiFangInfoRepository.findByCengAndPeiFangId(ceng, id);
    }
}
