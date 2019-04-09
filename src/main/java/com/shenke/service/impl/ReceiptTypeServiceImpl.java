package com.shenke.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.shenke.entity.ReceiptType;
import com.shenke.repository.ReceiptTypeRepository;
import com.shenke.service.ReceiptTypeService;

/**
 * 收付款方式Type Service实现类
 * 
 * @author Administrator
 *
 */
@Service("receiptTypeService")
public class ReceiptTypeServiceImpl implements ReceiptTypeService {

	@Resource
	private ReceiptTypeRepository recieReceiptTypeRepository;

	@Override
	public List<ReceiptType> findByParentId(Integer parentId) {
		return recieReceiptTypeRepository.findByParentId(parentId);
	}

	@Override
	public ReceiptType findById(Integer id) {
		return recieReceiptTypeRepository.findOne(id);
	}

	@Override
	public void save(ReceiptType ReceiptType) {
		recieReceiptTypeRepository.save(ReceiptType);
	}

	@Override
	public void delete(Integer id) {
		recieReceiptTypeRepository.delete(id);
	}

}
