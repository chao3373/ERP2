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
import com.shenke.entity.Group;
import com.shenke.repository.GroupRepository;
import com.shenke.service.GroupService;
import com.shenke.util.StringUtil;

/**
 * 班组设置实现类
 * 
 * @author Administrator
 *
 */
@Service("groupService")
public class GroupServiceImpl implements GroupService{
	
	@Resource
	private GroupRepository groupRepository;
	
	@Override
	public List<Group> list(Group group, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page-1, pageSize);
		Page<Group> pageUser = groupRepository.findAll(new Specification<Group>() {
			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if(group!=null) {
					if(StringUtil.isNotEmpty(group.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%"+group.getName()+"%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Group group) {
		Long count=groupRepository.count(new Specification<Group>() {

			@Override
			public Predicate toPredicate(Root<Group> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				if(group!=null){
					if(StringUtil.isNotEmpty(group.getName())){
						predicate.getExpressions().add(cb.like(root.get("name"), "%"+group.getName()+"%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}
			
		});
		return count;
	}

	@Override
	public Group findByGroupName(String name) {
		return groupRepository.findByGrouptName(name);
	}

	@Override
	public void save(Group entrepot) {
		groupRepository.save(entrepot);
	}

	@Override
	public Group findById(Integer id) {
		return groupRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		groupRepository.delete(id);
	}

	@Override
	public List<Group> findByName(String string) {
		return groupRepository.findByName(string);
	}


}
