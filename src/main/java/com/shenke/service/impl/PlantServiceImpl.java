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
import com.shenke.entity.Plant;
import com.shenke.repository.PlantRepository;
import com.shenke.service.PlantService;
import com.shenke.util.StringUtil;

/**
 * 厂商Service实现类
 * 
 * @author Administrator
 *
 */
@Service("plantService")
public class PlantServiceImpl implements PlantService {

	@Resource
	private PlantRepository plantRepository;

	@Override
	public List<Plant> findByPlantTypeId(Integer id) {
		return plantRepository.findByDepId(id);
	}

	@Override
	public List<Plant> list(Plant goods, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Plant> pageGoods = plantRepository.findAll(new Specification<Plant>() {
			@Override
			public Predicate toPredicate(Root<Plant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Long getCount(Plant goods) {
		Long count = plantRepository.count(new Specification<Plant>() {

			@Override
			public Predicate toPredicate(Root<Plant> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public void save(Plant plant) {
		plantRepository.save(plant);
	}

	@Override
	public Plant findById(Integer id) {
		return plantRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		plantRepository.delete(id);
	}

}
