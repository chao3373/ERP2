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

					System.out.println("*********************client!=null*******************************");
					System.out.println("*************************" + condition.get("client") + "***************************************");

					Subquery<Integer> subquery2 = query.subquery(Integer.class);
					Root<Client> fromC = subquery2.from(Client.class);
					subquery2.select(fromC.get("id")).where(cb.equal(fromC.get("name"), condition.get("client")));

					Subquery<Integer> subquery = query.subquery(Integer.class);
					Root<SaleList> fromSL = subquery.from(SaleList.class);
					subquery.select(fromSL.get("id")).where(fromSL.get("client").get("id").in(subquery2));

					predicate.getExpressions().add(cb.and(root.get("saleList").get("id").in(subquery)));
				}

				return predicate;
			}
		};

		if (condition.get("modeSort") != null) {
			if (condition.get("modeSort").equals("DESC")) {
				orders.add(new Order(Direction.DESC, "model"));
			} else {
				orders.add(new Order(Direction.ASC, "model"));
			}
		}
		if (condition.get("priceSort") != null) {
			if (condition.get("priceSort").equals("DESC")) {
				orders.add(new Order(Direction.DESC, "price"));
			} else {
				orders.add(new Order(Direction.ASC, "price"));
			}
		}
		if (condition.get("lengthSort") != null) {
			if (condition.get("lengthSort").equals("DESC")) {
				orders.add(new Order(Direction.DESC, "length"));
			} else {
				orders.add(new Order(Direction.ASC, "length"));
			}
		}

		if (orders.size() != 0) {
			return saleListProductRepository.findAll(specification, new Sort(orders));
		} else {
			return saleListProductRepository.findAll(specification);
		}
	}

}
