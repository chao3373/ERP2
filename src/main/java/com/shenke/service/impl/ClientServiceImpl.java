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
import com.shenke.entity.Client;
import com.shenke.repository.ClientRepository;
import com.shenke.service.ClientService;
import com.shenke.util.StringUtil;

/**
 * 客户Service实现类
 * 
 * @author Administrator
 *
 */
@Service("clientService")
public class ClientServiceImpl implements ClientService {
	
	@Resource
	private ClientRepository clientRepository;

	@Override
	public List<Client> findByClientTypeId(Integer id) {
		return clientRepository.findByClientTypeId(id);
	}

	@Override
	public List<Client> list(Client goods, Integer page, Integer pageSize, Direction direction, String... properties) {
		Pageable pageable = new PageRequest(page - 1, pageSize);
		Page<Client> pageGoods = clientRepository.findAll(new Specification<Client>() {
			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Long getCount(Client goods) {
		Long count = clientRepository.count(new Specification<Client>() {

			@Override
			public Predicate toPredicate(Root<Client> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public void save(Client Client) {
		clientRepository.save(Client);
	}

	@Override
	public Client findById(Integer id) {
		return clientRepository.findOne(id);
	}

	@Override
	public void deleteById(Integer id) {
		clientRepository.delete(id);
	}

	@Override
	public List<Client> findByName(String string) {
		return clientRepository.findByName(string);
	}

    @Override
    public Client findName(String clname) {
        return clientRepository.findByNam(clname);
    }
}
