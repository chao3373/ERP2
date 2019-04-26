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
import com.shenke.entity.Dao;
import com.shenke.repository.DaoRepository;
import com.shenke.service.DaoService;
import com.shenke.util.StringUtil;

/**
 * 剖刀Service实现类
 * 
 * @author Administrator
 *
 */
@Service("daoService")
public class DaoServiceImpl implements DaoService {
	
	@Resource
	private DaoRepository daoRepository;

	@Override
	public List<Dao> list(Dao dao, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Dao> pageUser = daoRepository.findAll(new Specification<Dao>() {
			@Override
			public Predicate toPredicate(Root<Dao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (dao != null) {
					if (StringUtil.isNotEmpty(dao.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + dao.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Dao dao) {
		Long count = daoRepository.count(new Specification<Dao>() {

			@Override
			public Predicate toPredicate(Root<Dao> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (dao != null) {
					if (StringUtil.isNotEmpty(dao.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + dao.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Dao findByDaoName(String name) {
		return daoRepository.findByDaoName(name);
	}

	@Override
	public void save(Dao dao) {
		daoRepository.save(dao);
	}

	@Override
	public Dao findById(Integer id) {
		return daoRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		daoRepository.delete(id);
	}

	@Override
	public List<Dao> findByName(String string) {
		return daoRepository.findByName(string);
	}

    @Override
    public Dao findName(String daoname) {
        return daoRepository.findByDaoName(daoname);
    }

}
