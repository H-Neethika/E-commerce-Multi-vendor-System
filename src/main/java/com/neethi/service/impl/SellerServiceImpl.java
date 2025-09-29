package com.neethi.service.impl;

import com.neethi.config.JwtProvider;
import com.neethi.domain.AccountStatus;
import com.neethi.domain.USER_ROLE;
import com.neethi.exceptions.SellerException;
import com.neethi.modal.Address;
import com.neethi.modal.Seller;
import com.neethi.repository.AddressRepository;
import com.neethi.repository.SellerRepository;
import com.neethi.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final AddressRepository addressRepository;

    @Override
    public Seller getSellerProfile(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return this.getSellerByEmail(email);
    }

    @Override
    public Seller createSeller(Seller seller) throws Exception {
        Seller sellerExist = sellerRepository.findByEmail(seller.getEmail());
        if (sellerExist != null) {
            throw new Exception("Seller already exist, use different email");
        }
        Address savedAddress = addressRepository.save(seller.getPickupAddress());

        Seller createdSeller = new Seller();
        createdSeller.setSellerName(seller.getSellerName());
        createdSeller.setEmail(seller.getEmail());
        createdSeller.setPassword(passwordEncoder.encode(seller.getPassword()));
        createdSeller.setMobile(seller.getMobile());
        createdSeller.setGSTIN(seller.getGSTIN());
        createdSeller.setRole(USER_ROLE.ROLE_SELLER);
        createdSeller.setPickupAddress(savedAddress);
        createdSeller.setBankDetails(seller.getBankDetails());
        createdSeller.setBusinessDetails(seller.getBusinessDetails());
        return sellerRepository.save(createdSeller);
    }

    @Override
    public Seller getSellerById(Long id) throws SellerException {
        return sellerRepository.findById(id).orElseThrow(() -> new SellerException("Seller not found with id " + id));
    }

    @Override
    public Seller getSellerByEmail(String email) throws Exception {
        Seller seller = sellerRepository.findByEmail(email);
        if (seller == null) {
            throw new Exception("No seller found with this email " + email);
        }
        return seller;
    }

    @Override
    public List<Seller> getAllSellers(AccountStatus status) {
        return sellerRepository.findByAccountStatus(status);
    }

    @Override
    public Seller updateSeller(Long id, Seller seller) throws Exception {
        Seller existSeller = sellerRepository.findById(id).orElseThrow(() -> new Exception("Seller not dound with this id " + id));

        if (seller.getSellerName() != null) {
            existSeller.setSellerName(seller.getSellerName());
        }
        if (seller.getMobile() != null) {
            existSeller.setMobile(seller.getMobile());
        }
        if (seller.getEmail() != null) {
            existSeller.setEmail(seller.getEmail());
        }
        if (seller.getBusinessDetails() != null && seller.getBusinessDetails().getBusinessName() != null) {
            existSeller.getBusinessDetails().setBusinessName(seller.getBusinessDetails().getBusinessName());
        }
        if (seller.getBankDetails() != null && seller.getBankDetails().getAccountHolderName() != null && seller.getBankDetails().getAccountNumber() != null && seller.getBankDetails().getIfscCode() != null) {
            existSeller.getBankDetails().setAccountHolderName(seller.getBankDetails().getAccountHolderName());
            existSeller.getBankDetails().setIfscCode(seller.getBankDetails().getIfscCode());
            existSeller.getBankDetails().setAccountNumber(seller.getBankDetails().getAccountNumber());
        }

        if (seller.getPickupAddress() != null && seller.getPickupAddress().getAddress() != null && seller.getPickupAddress().getMobile() != null && seller.getPickupAddress().getCity() != null && seller.getPickupAddress().getState() != null) {
            existSeller.getPickupAddress().setAddress(seller.getPickupAddress().getAddress());
            existSeller.getPickupAddress().setMobile(seller.getPickupAddress().getMobile());
            existSeller.getPickupAddress().setCity(seller.getPickupAddress().getCity());
            existSeller.getPickupAddress().setState(seller.getPickupAddress().getState());
            existSeller.getPickupAddress().setPinCode(seller.getPickupAddress().getPinCode());
        }


        if (seller.getGSTIN() != null) {
            existSeller.setGSTIN(seller.getGSTIN());
        }


        return sellerRepository.save(existSeller);
    }

    @Override
    public void deleteSeller(Long id) throws Exception {
        Seller seller = this.getSellerById(id);
        sellerRepository.delete(seller);

    }

    @Override
    public Seller verifyEmail(String email, String otp) throws Exception {
        Seller seller = getSellerByEmail(email);
        seller.setEmailVerified(true);
        return sellerRepository.save(seller);
    }

    @Override
    public Seller updateSellerAccountStatus(Long id, AccountStatus accountStatus) throws Exception {
        Seller seller = getSellerById(id);
        seller.setAccountStatus(accountStatus);
        return sellerRepository.save(seller);
    }
}
