package com.omna.nft;

import io.cucumber.java.en.When;

public class NftSteps extends CucumberSteps {

    @When("O usuario cadastra uma nova NFT")
    public void oUsuarioCadastraUmaNovaNFT() {
        System.out.println("O usuario cadastra uma nova NFT");
    }
}
