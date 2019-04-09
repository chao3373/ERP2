package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.Receipt;
import com.shenke.repository.ReceiptRepository;
import com.shenke.service.ReceiptService;
import com.shenke.util.StringUtil;

/**
 * 收付款方式Service实现类
 * 
 * @author Administrator
 *
 */
@Service("receiptService")
public class ReceiptServiceImpl implements ReceiptService {

	@Resource
	private ReceiptRepository reciReceiptRepository;

	@Override
	public List<Receipt> findByReceiptTypeId(Integer id) {
		return reciReceiptRepository.findByDepId(id);
	}

	@Override
	public List<Receipt> list(Receipt goods, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Receipt> pageGoods = reciReceiptRepository.findAll(new Specification<Receipt>() {
			@Override
			public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (goods != null) {
					if (StringUtil.isNotEmpty(goods.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + goods.getName() + "%"));
					}
					if (goods.getType() != null && goods.getType().getId() != null && goods.getType().getId() != 1) {
						predicate.getExpressions().add(cb.equal(root.get("type").get("id"), goods.getType().getId()));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageGoods.getContent();
	}

	@Override
	public Long getCount(Receipt goods) {
		Long count = reciReceiptRepository.count(new Specification<Receipt>() {

			@Override
			public Predicate toPredicate(Root<Receipt> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (goods != null) {
					if (StringUtil.isNotEmpty(goods.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + goods.getName() + "%"));
					}
					if (goods.getType() != null && goods.getType().getId() != null && goods.getType().getId() != 1) {
						predicate.getExpressions().add(cb.equal(root.get("type").get("id"), goods.getType().getId()));
					}
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public void save(Receipt Receipt) {
		reciReceiptRepository.save(Receipt);
	}

	@Override
	public Receipt findById(Integer id) {
		return reciReceiptRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		reciReceiptRepository.delete(id);
	}
}
