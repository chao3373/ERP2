package com.shenke.service.impl;

import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;

import com.shenke.entity.*;
import com.shenke.repository.*;
import com.shenke.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.shenke.service.StorageService;

/**
 * 入库单Service实现类
 *
 * @author Administrator
 */
@Service("storageService")
@Transactional
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageRepository storageRepository;

    @Resource
    private SaleListProductRepository saleListProductRepository;

    @Resource
    private JitaiProductionAllotRepository jitaiProductionAllotRepository;

    @Resource
    private JiTaiRepository jiTaiRepository;

    @Resource
    private ClerkRepository clerkRepository;

    @Override
    public void add(Double weight, Integer saleListProductId, Integer jitaiProductionAllotId,
                    Integer producionProcessId, Integer jitaiId, String clerkName) {

        SaleListProduct saleListProduct = saleListProductRepository.findOne(saleListProductId);
        JitaiProductionAllot jitaiProductionAllot = jitaiProductionAllotRepository.findOne(jitaiProductionAllotId);
        JiTai jiTai = jiTaiRepository.findOne(jitaiId);
         Integer count = saleListProduct.getAccomplishNumber();
        Clerk clerk = clerkRepository.findByNam(clerkName);
        if (count == null || count == 0) {
            count = 1;
            saleListProductRepository.updateAccomplishNumberById(count, saleListProductId);
        } else if (count == saleListProduct.getNum() - 1) {
            count += 1;
            saleListProductRepository.updateAccomplishNumberById(count, saleListProductId);
            saleListProductRepository.updateState("生产完成：" + jiTai.getName(), saleListProductId);
            saleListProductRepository.updateIussueState("生产完成：" + jiTai.getName(), saleListProductId);
        } else {
            count += 1;
            saleListProductRepository.updateAccomplishNumberById(count, saleListProductId);
        }

        jitaiProductionAllotRepository.updateStateById("生产完成：" + jiTai.getName(), jitaiProductionAllotId);

        Storage storage = new Storage();

        storage.setAccomplishState("完成");
        storage.setAllorTime(jitaiProductionAllot.getAllorTime());
        storage.setAllotState(jitaiProductionAllot.getAllotState());
        storage.setBrand(saleListProduct.getBrand());
        storage.setClientname(saleListProduct.getClientname());
        storage.setColor(saleListProduct.getColor());
        storage.setDao(saleListProduct.getDao());
        storage.setDemand(saleListProduct.getDemand());
        storage.setInformNumber(jitaiProductionAllot.getInformNumber());
        storage.setIssueState(jitaiProductionAllot.getIssueState());
        storage.setJiTai(jiTai);
        storage.setJitaiProductionAllot(jitaiProductionAllot);
        storage.setLabel(saleListProduct.getLabel());
        storage.setLength(saleListProduct.getLength());
        storage.setLetter(saleListProduct.getLetter());
        storage.setMeter(saleListProduct.getMeter());
        storage.setModel(saleListProduct.getModel());
        storage.setName(saleListProduct.getName());
        storage.setNum(saleListProduct.getNum());
        storage.setNumsquare(saleListProduct.getNumsquare());
        storage.setOneweight(saleListProduct.getOneweight());
        storage.setPack(saleListProduct.getPack());
        storage.setPatu(saleListProduct.getPatu());
        storage.setPeasant(saleListProduct.getPeasant());
        storage.setPrice(saleListProduct.getPrice());
        storage.setProductionMessage(jitaiProductionAllot.getProductionMessage());
        storage.setRealitymodel(saleListProduct.getRealitymodel());
        storage.setRealityprice(saleListProduct.getRealityprice());
        storage.setRealityweight(weight);
        storage.setSaleList(saleListProduct.getSaleList());
        storage.setSaleListProduct(saleListProduct);
        storage.setSaleNumber(saleListProduct.getSaleList().getSaleNumber());
        storage.setSquare(saleListProduct.getSquare());
        storage.setState(saleListProduct.getState());
        storage.setSumwight(saleListProduct.getSumwight());
        storage.setTaskQuantity(jitaiProductionAllot.getTaskQuantity());
        storage.setTheoryweight(saleListProduct.getTheoryweight());
        storage.setWeight(saleListProduct.getWeight());
        storage.setWeightway(saleListProduct.getWeightway());
        storage.setWightset(saleListProduct.getWightset());
        storage.setDateInProduced(new Date(System.currentTimeMillis()));
        storage.setClerk(clerk);

        storageRepository.save(storage);

    }

    @Override
    public List<Storage> fandAll() {
        return storageRepository.findAll();
    }

    @Override
    public List<Storage> fandAllBySerialNumber(String serialNumber) {
        return storageRepository.fandAllBySerialNumber(serialNumber);
    }

    @Override
    public Storage selectByMaxId() {
        return storageRepository.selectByMaxId();
    }

    @Override
    public void outStorage(int id, Date date) {
        // TODO Auto-generated method stub
        storageRepository.outStorage(id, date);
        saleListProductRepository.updateState("提货:" + storageRepository.findOne(id).getJiTai().getName(), storageRepository.findOne(id).getSaleListProduct().getId());
    }

    @Override
    public List<Storage> outSuccess() {
        // TODO Auto-generated method stub
        return storageRepository.outSuccess();
    }


    @Override
    public void gai(String storage_number, int id) {
        // TODO Auto-generated method stub
        storageRepository.gai(storage_number, id);
    }

    @Override
    public void updateStateById(String state, Integer id, Date date) {
        storageRepository.updateStateById(state, id, date);
    }

    @Override
    public List<Object[]> findByClientAndGroupByName(String client) {
        List<Object[]> byClientAndGroupByName = storageRepository.findByClientAndGroupByName(client);
        System.out.println("==============================");
        System.out.println(byClientAndGroupByName.size());
        System.out.println(byClientAndGroupByName);
        for (Object[] obj : byClientAndGroupByName) {
            for (int i = 0; i < obj.length; i++) {
                System.out.println(obj[i]);
            }
        }
        return byClientAndGroupByName;
    }

    @Override
    public List<Storage> findAll() {
        return storageRepository.findAll();
    }

    @Override
    public Storage findById(Integer s) {
        return storageRepository.findOne(s);
    }

    @Override
    public String getTodayMaxOutNumber() {
        // TODO Auto-generated method stub
        return  storageRepository.getTodayMaxOutNumber();
    }

    @Override
    public List<JieSuan> FindByGroup(String client) {
        System.out.println(client);
        System.out.println(storageRepository.FindByGroup(client));
        List<JieSuan> castEntity = EntityUtils.castEntity(storageRepository.FindByGroup(client), JieSuan.class);
        return castEntity;
    }

}
