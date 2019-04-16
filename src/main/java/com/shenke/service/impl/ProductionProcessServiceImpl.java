package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.ProductionProcess;
import com.shenke.repository.ProductionProcessRepository;
import com.shenke.service.ProductionProcessService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;

/**
 * 生产加工单Service实现类
 * 
 * @author Administrator
 *
 */
@Service("productionProcessService")
@Transactional
public class ProductionProcessServiceImpl implements ProductionProcessService {

	@Resource
	private ProductionProcessRepository productionProcessRepository;

	@Override
	public void save(ProductionProcess processpProcess) {
		productionProcessRepository.save(processpProcess);
	}

	@Override
	public List<ProductionProcess> selectByJitaiId(Integer jitaiId) {
		return productionProcessRepository.selectByJitaiId(jitaiId);
	}

	@Override
	public List<ProductionProcess> selectAllByInformAndJitaiId(Integer jitai, Long infor) {
		return productionProcessRepository.findAll(new Specification<ProductionProcess>() {

			@Override
			public Predicate toPredicate(Root<ProductionProcess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), jitai));
				if (infor != null) {
					predicate.getExpressions().add(cb.equal(root.get("informNumber"), infor));
				}
				return predicate;
			}
		});
	}

	@Override
	public List<ProductionProcess> selectByIssueState(String issueState) {
		return productionProcessRepository.selectByIssueState(issueState);
	}

	@Override
	public List<ProductionProcess> screen(String allorTime, Integer jitai) {

		return productionProcessRepository.findAll(new Specification<ProductionProcess>() {

			@Override
			public Predicate toPredicate(Root<ProductionProcess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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

	@Override
	public List<ProductionProcess> selectByJitaiIdAndIssueStateAndInformNumber(Integer jitaiId, String issueState,
			Long informNumber) {
		System.out.println("jitaiId" + jitaiId);
		System.out.println("issueState" + issueState);
		System.out.println("informNumber" + informNumber);
		return productionProcessRepository.findAll(new Specification<ProductionProcess>() {

			@Override
			public Predicate toPredicate(Root<ProductionProcess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (jitaiId != null) {
					predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), jitaiId));
				}
				if (informNumber != null) {
					predicate.getExpressions().add(cb.equal(root.get("informNumber"), informNumber));
				}
				predicate.getExpressions().add(cb.like(root.get("state"), issueState));
				return predicate;
			}
		});
	}

	@Override
	public List<ProductionProcess> selectNoAccomplish(Integer jitaiId) {
		return productionProcessRepository.selectNoAccomplish(jitaiId);
	}

	@Override
	public void updateState(String string, Integer id) {
		productionProcessRepository.updateState(string, id);
	}

	@Override
	public void updateStateBySaleListProductId(String string, Integer id) {
		productionProcessRepository.updateIssueStateBySaleListProductId(string, id);
		productionProcessRepository.updateStateBySaleListProductId(string, id);
	}

}
