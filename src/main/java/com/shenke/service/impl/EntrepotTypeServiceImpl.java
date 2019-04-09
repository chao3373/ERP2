package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.shenke.entity.EntrepotType;
import com.shenke.entity.Entrepot;
import com.shenke.repository.EntrepotTypeRepository;
import com.shenke.service.EntrepotService;
import com.shenke.service.EntrepotTypeService;

/**
 * 仓位Type Service实现类
 * 
 * @author Administrator
 *
 */
@Service("entrepotTypeService")
public class EntrepotTypeServiceImpl implements EntrepotTypeService {

	@Resource
	private EntrepotTypeRepository entrepotTypeRepository;
	
	

	@Override
	public List<EntrepotType> findByParentId(Integer parentId) {
		return entrepotTypeRepository.findByParentId(parentId);
	}

	@Override
	public EntrepotType findById(Integer id) {
		return entrepotTypeRepository.findOne(id);
	}

	@Override
	public void save(EntrepotType EntrepotType) {
		entrepotTypeRepository.save(EntrepotType);
	}

	@Override
	public void delete(Integer id) {
		entrepotTypeRepository.delete(id);
	}

}
