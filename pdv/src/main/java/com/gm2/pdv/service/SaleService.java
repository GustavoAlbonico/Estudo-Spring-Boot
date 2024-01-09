package com.gm2.pdv.service;

import com.gm2.pdv.dto.ProductDTO;
import com.gm2.pdv.dto.SaleDTO;
import com.gm2.pdv.dto.SaleInfoDTO;
import com.gm2.pdv.entity.ItemSale;
import com.gm2.pdv.entity.Product;
import com.gm2.pdv.entity.Sale;
import com.gm2.pdv.entity.User;
import com.gm2.pdv.repository.ItemSaleRepository;
import com.gm2.pdv.repository.ProductRepository;
import com.gm2.pdv.repository.SaleRepository;
import com.gm2.pdv.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;

//    public List<SaleInfoDTO> findAll(){
//
//    }


    @Transactional
    public long save(SaleDTO sale) {

        User user = userRepository.findById(sale.getUserid()).get();

        Sale newSale = new Sale();
        newSale.setUser(user);
        newSale.setDate(LocalDate.now());

        List<ItemSale> items = getItemSale(sale.getItems());

        newSale = saleRepository.save(newSale);

        saveItemSale(items, newSale);

        return newSale.getId();

    }

    private void saveItemSale(List<ItemSale> items, Sale newSale) {
        for(ItemSale item: items){
            item.setSale(newSale);
            itemSaleRepository.save(item);
        }
    }

    private List<ItemSale> getItemSale(List<ProductDTO> products) {

        return products.stream().map(item -> {
            Product product = productRepository.getReferenceById(item.getProductid());

            ItemSale itemSale = new ItemSale();
            itemSale.setProduct(product);
            itemSale.setQuantity(item.getQuantity());

            return itemSale;

        }).collect(Collectors.toList());
    }
}
