package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shenke.entity.ClientType;
import com.shenke.repository.ClientTypeRepository;
import com.shenke.service.ClientTypeService;

/**
 * 客户关系Service实现类
 * 
 * @author Administrator
 *
 */
@Service("clientTypeService")
public class ClientTypeServiceImpl implements ClientTypeService {

	@Resource
	private ClientTypeRepository clientTypeRepository;

	@Override
	public List<ClientType> findByParentId(Integer parentId) {
		return clientTypeRepository.findByParentId(parentId);
	}

	@Override
	public ClientType findById(Integer id) {
		return clientTypeRepository.findOne(id);
	}

	@Override
	public void save(ClientType ClientType) {
		clientTypeRepository.save(ClientType);
	}

	@Override
	public void delete(Integer id) {
		clientTypeRepository.delete(id);
	}

}
