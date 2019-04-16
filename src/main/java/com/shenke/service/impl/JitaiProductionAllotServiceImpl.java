package com.shenke.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;
import javax.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.shenke.entity.JiTai;
import com.shenke.entity.JitaiProductionAllot;
import com.shenke.entity.SaleListProduct;
import com.shenke.repository.JiTaiRepository;
import com.shenke.repository.JitaiProductionAllotRepository;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.JitaiProductionAllotService;
import com.shenke.util.DateUtil;
import com.shenke.util.StringUtil;

/**
 * 机台生产分配Service实现类
 * 
 * @author Administrator
 *
 */
@Transactional
@Service("jitaiProductionAllotService")
public class JitaiProductionAllotServiceImpl implements JitaiProductionAllotService {

	@Resource
	private JitaiProductionAllotRepository jitaiProductionAllotRepository;

	@Resource
	private JiTaiRepository jiTaiRepository;

	@Resource
	private SaleListProductRepository saleListProductRepository;

	@Override
	public void save(JitaiProductionAllot jitaiProductionAllot) {
		jitaiProductionAllotRepository.save(jitaiProductionAllot);
	}

	@Override
	public Long findMaxInfornNumber() {
		return jitaiProductionAllotRepository.findMaxInfornNumber();
	}

	@Override
	public List<JitaiProductionAllot> list() {
		return jitaiProductionAllotRepository.findAll();
	}

	@Override
	public List<JitaiProductionAllot> screen(String allorTime, Integer jitai) {

		return jitaiProductionAllotRepository.findAll(new Specification<JitaiProductionAllot>() {

			@Override
			public Predicate toPredicate(Root<JitaiProductionAllot> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				if (StringUtil.isNotEmpty(allorTime)) {
					try {
						predicate.getExpressions()
								.add(cb.equal(root.get("allorTime"), DateUtil.formatString(allorTime, "yyyy-MM-dd")));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				if (jitai != null) {
					predicate.getExpressions().add(cb.equal(root.get("jiTai").get("id"), jitai));
				}
				query.groupBy(root.get("saleListProduct"));
				return predicate;
			}
		});
	}

	@Override
	public void issue(String name, int parseInt) {
		jitaiProductionAllotRepository.issue(name, parseInt);
	}

	@Override
	public Integer countBySaleListProductId(Integer id) {
		return jitaiProductionAllotRepository.countBySaleListProductId(id);
	}

	@Override
	public List<JitaiProductionAllot> findBySaleListProductId(Integer id) {
		return jitaiProductionAllotRepository.findBySaleListProductId(id);
	}

	@Override
	public void updateNum(Integer countSaleListProduct, int id) {
		jitaiProductionAllotRepository.updateNum(countSaleListProduct, id);
	}

	@Override
	public List<JitaiProductionAllot> listGroubBy() {
		return jitaiProductionAllotRepository.listGroubBy();
	}

	@Override
	public void updateJitai(int parseInt, Integer jiTai2) {
		jitaiProductionAllotRepository.updateJitai(parseInt, jiTai2);
		JiTai jiTai = jiTaiRepository.findOne(jiTai2);
		jitaiProductionAllotRepository.updateAllotState("分配机台：" + jiTai.getName(), parseInt);
	}

	@Override
	public JitaiProductionAllot findOne(Integer informNumber) {
		return jitaiProductionAllotRepository.findOne(informNumber);
	}

	@Override
	public List<JitaiProductionAllot> findByImformNubers(int parseInt) {
		return jitaiProductionAllotRepository.findByImformNubers(parseInt);
	}

	@Override
	public JitaiProductionAllot findById(Integer id) {
		return jitaiProductionAllotRepository.findOne(id);
	}

	@Override
	public List<SaleListProduct> selectSaleListProductByJitaiId(Integer jitaiId) {
		Set<Integer> saleListProductIds = new HashSet<Integer>();
		List<JitaiProductionAllot> jitaiProductionAllots = jitaiProductionAllotRepository.findByJitaiId(jitaiId);
		for (JitaiProductionAllot jitaiProductionAllot : jitaiProductionAllots) {
			saleListProductIds.add(jitaiProductionAllot.getSaleListProduct().getId());
		}

		return saleListProductRepository.findAll(saleListProductIds);

	}

	@Override
	public Set<Integer> selectAllInformByJitaiId(Integer jitaiId) {
		return jitaiProductionAllotRepository.selectAllInformByJitaiId(jitaiId);
	}

	@Override
	public List<SaleListProduct> selectAllByInformAndJitaiId(Integer jitai, String parseLong) {
		return saleListProductRepository.selectAllByInformAndJitaiId(jitai, Long.parseLong(parseLong));

	}

	@Override
	public List<JitaiProductionAllot> selectBySaleListProductId(Integer id) {
		return jitaiProductionAllotRepository.selectBySaleListProductId(id);
	}

}
