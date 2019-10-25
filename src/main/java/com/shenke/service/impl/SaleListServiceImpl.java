package com.shenke.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.*;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.shenke.entity.SaleList;
import com.shenke.entity.SaleListProduct;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.repository.SaleListRepository;
import com.shenke.service.SaleListService;
import com.shenke.util.StringUtil;

import javassist.expr.NewArray;

/**
 * 销售单Service实现类
 * 
 * @author Administrator
 *
 */
@Service("saleListService")
public class SaleListServiceImpl implements SaleListService {

	@Resource
	private SaleListRepository saleListRepository;

	@Resource
	private SaleListProductRepository saleListProductRepository;

	@Override
	public String getTodayMaxSaleNumber() {
		return saleListRepository.getTodayMaxSaleNumber();
	}

	@Override
	public void save(SaleList saleList, List<SaleListProduct> plgList) {
		saleListRepository.save(saleList);
		for (SaleListProduct saleListProduct : plgList) {
			saleListProductRepository.save(saleListProduct);
		}
	}

	@Override
	public List<SaleList> list(SaleList saleList, Direction direction, String... properties) {

		return saleListRepository.findAll(new Specification<SaleList>() {

			@Override
			public Predicate toPredicate(Root<SaleList> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (saleList != null) {
					if (StringUtil.isNotEmpty(saleList.getSaleNumber())) {
						predicate.getExpressions()
								.add(cb.like(root.get("saleNumber"), "%" + saleList.getSaleNumber() + "%"));
					}
					if (saleList.getClient() != null && saleList.getClient().getId() != null) {
						predicate.getExpressions()
								.add(cb.equal(root.get("client").get("id"), saleList.getClient().getId()));
					}
					if (saleList.getClerk() != null && saleList.getClerk().getId() != null) {
						predicate.getExpressions()
								.add(cb.equal(root.get("clerk").get("id"), saleList.getClerk().getId()));
					}
					if (saleList.getbSaleDate() != null) {
						predicate.getExpressions()
								.add(cb.greaterThanOrEqualTo(root.get("saleDate"), saleList.getbSaleDate()));
					}
					if (saleList.getSaleDate() != null) {
						predicate.getExpressions()
								.add(cb.lessThanOrEqualTo(root.get("saleDate"), saleList.getSaleDate()));
					}
//只显示未审核的
//					Subquery<String> subquery = query.subquery(String.class);
//					Root<SaleListProduct> from = subquery.from(SaleListProduct.class);
//					subquery.select(from.get("saleList").get("id")).where(cb.like(from.get("state"), "%下单%"));
//
//					predicate.getExpressions().add(cb.and(root.get("id").in(subquery)));
				}
				return predicate;
			}
		}, new Sort(direction, properties));
	}

	@Override
	public void deleteByid(Integer id) {
		saleListProductRepository.deleteBySaleListId(id);
		saleListRepository.delete(id);
	}

	@Override
	public List<SaleListProduct> listProductByState(String state) {
		return saleListProductRepository.listProductByState(state);
	}

	@Override
	public List<SaleListProduct> listProductByStateAndId(Integer id, String state) {
		return saleListProductRepository.listProductByStateAndId(id, state);
	}

	@Override
	public List<Integer> getSaleListNo(int id) {
		return saleListRepository.getSaleListNo();
	}

	@Override
	public void saveOne(SaleList saleList) {
		saleListRepository.save(saleList);
	}

    @Override
    public void saveTwo(SaleList saleList) {
		saleListRepository.save(saleList);
    }

    @Override
    public List<SaleList>  findSaleListId(String saleNumber){
		return saleListRepository.findSaleListId(saleNumber);
	}

    @Override
    public SaleList findBySaleNumber(String danhao) {
        return saleListRepository.findBySaleNumber(danhao);
    }

    @Override
    public SaleList findById(Integer id) {
        return saleListRepository.findOne(id);
    }

    @Override
    public void updateDingjin(Double dingjin, Integer id) {
        saleListRepository.updateDingjin(dingjin, id);
    }
}
