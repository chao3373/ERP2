package com.shenke.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.util.StringUtil;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.Client;
import com.shenke.entity.SaleList;
import com.shenke.entity.SaleListProduct;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.SaleListProductService;

@Service("saleListProductService")
public class SaleListProductServiceImpl implements SaleListProductService {

	@Resource
	private SaleListProductRepository saleListProductRepository;

	@Override
	public List<SaleListProduct> listBySaleListId(Integer saleListId) {
		return saleListProductRepository.listBySaleListId(saleListId);
	}

	@Override
	public void deleteBySaleListId(Integer id) {
		saleListProductRepository.deleteBySaleListId(id);
	}

	@Override
	public void passTheAudit(int id) {
		saleListProductRepository.passTheAudit(id);
	}

	@Override
	public void auditFailure(int id, String cause) {
		saleListProductRepository.auditFailure(id, cause);
	}

	@Override
	public List<SaleListProduct> fandAll() {
		return saleListProductRepository.findAll();
	}
	
	public List<SaleListProduct> fandAll(Iterable<Integer> ids) {
		return saleListProductRepository.findAll(ids);
	}
	@Override
	public List<SaleListProduct> listProductSucceed() {
		return saleListProductRepository.listProductSucceed();
	}

	@Override
	public List<SaleListProduct> breadthList(String q) {
		return saleListProductRepository.breadthList(q);
	}

	@Override
	public List<SaleListProduct> screen(Map<String, Object> condition) {


		List<Order> orders = new ArrayList<Order>();

		Specification<SaleListProduct> specification = new Specification<SaleListProduct>() {
			Predicate predicate = null;

			@Override
			public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				predicate = cb.conjunction();
				predicate.getExpressions().add(cb.like(root.get("state"), "%审核通过%"));
				if (condition.get("client") != null) {

					Subquery<Integer> subquery2 = query.subquery(Integer.class);
					Root<Client> fromC = subquery2.from(Client.class);
					subquery2.select(fromC.get("id")).where(cb.equal(fromC.get("name"), condition.get("client")));

					Subquery<Integer> subquery = query.subquery(Integer.class);
					Root<SaleList> fromSL = subquery.from(SaleList.class);
					subquery.select(fromSL.get("id")).where(fromSL.get("client").get("id").in(subquery2));

					predicate.getExpressions().add(cb.and(root.get("saleList").get("id").in(subquery)));
				}

				if (condition.get("modeSort") != null) {
					predicate.getExpressions().add(cb.and(cb.equal(root.get("model"), condition.get("modeSort"))));
				}
				if (condition.get("priceSort") != null) {
					predicate.getExpressions().add(cb.and(cb.equal(root.get("price"), condition.get("priceSort"))));
				}
				if (condition.get("lengthSort") != null) {
					predicate.getExpressions().add(cb.and(cb.equal(root.get("length"), condition.get("lengthSort"))));
				}
				if (condition.get("realityprice") != null) {
					predicate.getExpressions().add(cb.and(cb.equal(root.get("realityprice"), condition.get("realityprice"))));
				}
				if (condition.get("oneweight") != null) {
					predicate.getExpressions().add(cb.and(cb.equal(root.get("oneweight"), condition.get("oneweight"))));
				}
				if (condition.get("sumwight") != null) {
					predicate.getExpressions().add(cb.and(cb.equal(root.get("sumwight"), condition.get("sumwight"))));
				}
				if (condition.get("realitymodel") != null) {
					predicate.getExpressions()
							.add(cb.and(cb.equal(root.get("realitymodel"), condition.get("realitymodel"))));
				}

				return predicate;
			}
		};

		return saleListProductRepository.findAll(specification);

	}

	@Override
	public void updateState(String name, Integer id) {
		saleListProductRepository.updateState(name, id);
	}

    @Override
    public void saveList(List<SaleListProduct> plgList) {
		for (SaleListProduct saleListProduct : plgList) {
			saleListProductRepository.save(saleListProduct);
		}
    }

    @Override
    public void updateJitaiId(Integer id, Integer id1) {
        saleListProductRepository.updateJitaiId(id, id1);
    }

	@Override
	public List<SaleListProduct> selectByJitaiIdAndIssueStateAndInformNumber(Integer jitaiId, String state, Long infLong) {
		return saleListProductRepository.findAll(new Specification<SaleListProduct>() {
			@Override
			public Predicate toPredicate(Root<SaleListProduct> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(jitaiId != null) {
					predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), jitaiId));
				}
				if (StringUtil.isNotEmpty(state)) {
					predicate.getExpressions().add(cb.like(root.get("issueState"), "%" + state +"%"));
				}
				if (infLong!=null) {
					predicate.getExpressions().add(cb.equal(root.get("informNumber"), infLong));
				}
				return predicate;
			}
		});
	}

	@Override
	public void updateInformNumber(Long informNumber, int id) {
		saleListProductRepository.updateInformNumber(informNumber, id);
	}

	@Override
	public void updateIussueState(String issueState, int id) {
		saleListProductRepository.updateIussueState(issueState, id);
	}

	@Override
	public List<SaleListProduct> selectNoAccomplish(Integer jitaiId) {
		return saleListProductRepository.selectNoAccomplish(jitaiId);
	}

}
