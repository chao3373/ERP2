package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.repository.JitaiProductionAllotRepository;
import com.shenke.service.JitaiProductionAllotService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;

/**
 * 机台生产分配Service实现类
 * 
 * @author Administrator
 *
 */
@Service("jitaiProductionAllotService")
public class JitaiProductionAllotServiceImpl implements JitaiProductionAllotService {

	@Resource
	private JitaiProductionAllotRepository jitaiProductionAllotRepository;

	@Override
	public void save(JitaiProductionAllot jitaiProductionAllot) {
		jitaiProductionAllotRepository.save(jitaiProductionAllot);
	}

	@Override
	public Long findMaxInfornNumber() {
		return jitaiProductionAllotRepository.findMaxInfornNumber();
	}

	@Override
	public List<JitaiProductionAllot> list() {
		return jitaiProductionAllotRepository.findAll();
	}

	@Override
	public List<JitaiProductionAllot> screen(String allorTime, Integer jitai) {

		return jitaiProductionAllotRepository.findAll(new Specification<JitaiProductionAllot>() {

			@Override
			public Predicate toPredicate(Root<JitaiProductionAllot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (StringUtil.isNotEmpty(allorTime)) {
					try {
						predicate.getExpressions()
								.add(cb.equal(root.get("allorTime"), DateUtil.formatString(allorTime, "yyyy-MM-dd")));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (jitai != null) {
					predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), jitai));
				}
				return predicate;
			}
		});
	}

}
