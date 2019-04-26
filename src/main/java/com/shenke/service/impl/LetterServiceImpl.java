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
import com.shenke.entity.Letter;
import com.shenke.repository.LetterRepository;
import com.shenke.service.LetterService;
import com.shenke.util.StringUtil;

/**
 * 印字设置Service实现类
 * @author Administrator
 *
 */
@Service("letterService")
public class LetterServiceImpl implements LetterService{
	
	@Resource
	private LetterRepository letterRepository;
	
	@Override
	public List<Letter> list(Letter letter, Integer page, Integer pageSize, Direction asc, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Letter> pageUser = letterRepository.findAll(new Specification<Letter>() {
			@Override
			public Predicate toPredicate(Root<Letter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (letter != null) {
					if (StringUtil.isNotEmpty(letter.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + letter.getName() + "%"));
					}
				}
				return predicate;
			}
		}, pageable);
		return pageUser.getContent();
	}

	@Override
	public Long getCount(Letter letter) {
		Long count = letterRepository.count(new Specification<Letter>() {

			@Override
			public Predicate toPredicate(Root<Letter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (letter != null) {
					if (StringUtil.isNotEmpty(letter.getName())) {
						predicate.getExpressions().add(cb.like(root.get("name"), "%" + letter.getName() + "%"));
					}
					predicate.getExpressions().add(cb.notEqual(root.get("id"), 1));
				}
				return predicate;
			}

		});
		return count;
	}

	@Override
	public Letter findByLetterName(String name) {
		return letterRepository.findByLetterName(name);
	}

	@Override
	public void save(Letter letter) {
		letterRepository.save(letter);
	}

	@Override
	public Letter findById(Integer id) {
		return letterRepository.findOne(id);
	}

	@Override
	public void delete(Integer id) {
		letterRepository.delete(id);
	}

	@Override
	public List<Letter> findByName(String string) {
		return letterRepository.findByName(string);
	}

    @Override
    public Letter findName(String lettername) {
        return letterRepository.findByLetterName(lettername);
    }
}
