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
import com.shenke.entity.Location;
import com.shenke.repository.LocationRepository;
import com.shenke.service.LocationService;
import com.shenke.util.StringUtil;

@Service("locationService")
public class LocationServiceImpl implements LocationService {
	
	@Resource
	private LocationRepository locationRepository;

	@Override
	public List<Location> list(Location Location, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Location> pageUser = locationRepository.findAll(new Specification<Location>() {
			@Override
			public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (Location != null) {
					if (StringUtil.isNotEmpty(Location.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + Location.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}


	@Override
	public Long getCount(Location location) {
		Long count = locationRepository.count(new Specification<Location>() {

			@Override
			public Predicate toPredicate(Root<Location> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (location != null) {
					if (StringUtil.isNotEmpty(location.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + location.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Location findByLocationName(String name) {
		return locationRepository.findByLocationName(name);

	}

	@Override
	public void save(Location location) {
		System.err.println(location);
		locationRepository.save(location);
		
	}

	@Override
	public void delete(Integer id) {
		locationRepository.delete(id);
	}

	@Override
	public Location findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Location> findByName(String string) {
		return locationRepository.findByName(string);
	}

	


}
