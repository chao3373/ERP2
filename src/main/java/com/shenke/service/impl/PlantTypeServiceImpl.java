package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shenke.entity.PlantType;
import com.shenke.repository.PlantTypeRepository;
import com.shenke.service.PlantTypeService;

/**
 * 厂商关系Service实现类
 * 
 * @author Administrator
 *
 */
@Service("plantTypeService")
public class PlantTypeServiceImpl implements PlantTypeService {
	
	@Resource
	private PlantTypeRepository plantTypeRepository;

	@Override
	public List<PlantType> findByParentId(Integer parentId) {
		return plantTypeRepository.findByParentId(parentId);
	}

	@Override
	public PlantType findById(Integer id) {
		return plantTypeRepository.findOne(id);
	}

	@Override
	public void save(PlantType PlantType) {
		plantTypeRepository.save(PlantType);
	}

	@Override
	public void delete(Integer id) {
		plantTypeRepository.delete(id);
	}

}
