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
import com.shenke.entity.Pack;
import com.shenke.repository.PackRepository;
import com.shenke.service.PackService;
import com.shenke.util.StringUtil;

/**
 * 包装设置实现类
 * 
 * @author Administrator
 *
 */
@Service("packService")
public class PackServiceImpl implements PackService {
	
	@Resource
	private PackRepository packRepository;
	
	@Override
	public List<Pack> list(Pack pack, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Pack> pageUser = packRepository.findAll(new Specification<Pack>() {
			@Override
			public Predicate toPredicate(Root<Pack> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (pack != null) {
					if (StringUtil.isNotEmpty(pack.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + pack.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Pack Pack) {
		Long count = packRepository.count(new Specification<Pack>() {

			@Override
			public Predicate toPredicate(Root<Pack> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (Pack != null) {
					if (StringUtil.isNotEmpty(Pack.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + Pack.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Pack findByPackName(String name) {
		return packRepository.findByPackName(name);
	}

	@Override
	public void save(Pack Pack) {
		packRepository.save(Pack);
	}

	@Override
	public Pack findById(Integer id) {
		return packRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		packRepository.delete(id);
	}

	@Override
	public List<Pack> findByName(String string) {
		return packRepository.findByName(string);
	}

    @Override
    public Pack findName(String packname) {
        return packRepository.findByPackName(packname);
    }

}
