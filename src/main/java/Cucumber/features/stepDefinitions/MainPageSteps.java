package Cucumber.features.stepDefinitions;

import config.testConfigs.TestContext;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.MainPage;

public class MainPageSteps {

    private final TestContext context;

    MainPage mainPage;

    public MainPageSteps(TestContext context) {
        this.context = context;
        this.mainPage = new MainPage();
    }

    @When("'View Product' is clicked for product {int}")
    public void view_product_is_clicked_for_product(int index) {
        mainPage.clickViewProduct(index);
    }

    @Then("the product {int} detail page is opened")
    public void the_product_detail_page_is_opened(int index) {
        Assert.assertEquals("Product details page is wrong",
                "https://automationexercise.com/product_details/" + index,
                context.getDriver().getCurrentUrl());
    }

    @Given("the product {int} detail page was opened")
    public void the_product_detail_page_was_opened(int index) {
        context.getDriver().get("https://automationexercise.com/product_details/" + index);
    }

    @Given("{int} products are added to the cart")
    public void products_are_added_to_the_cart(int quantity) {
        mainPage.handleAds();

        for (int i = 0; i < quantity; i++) {
            mainPage.clickAddToCart(i+1);
            mainPage.clickContinueShopping();
        }
    }

    @Then("the recommended items are visible")
    public void the_recommended_items_are_visible() {
        Assert.assertTrue("The 'Recommended Items' title is not visible.",
                mainPage.isRecommendedItemsTitleVisible());
        Assert.assertTrue("The images of recommended items are not visible.",
                mainPage.areRecommendedItemsImagesVisible());
        Assert.assertTrue("The prices of recommended items are not visible.",
                mainPage.areRecommendedItemsPricesVisible());
        Assert.assertTrue("The names of recommended items are not visible.",
                mainPage.areRecommendedItemsNamesVisible());
        Assert.assertTrue("The 'Add to Cart' buttons of recommended items are not visible.",
                mainPage.areRecommendedItemsAddToCartVisible());
    }

    @And("the user adds all recommended products to the cart")
    public void the_user_adds_all_recommended_products_to_the_cart() {
        context.setAddedProductsNames(mainPage.getRecommendedItemsNames());
        mainPage.addAllRecommendedItemsToCart();
    }
}
