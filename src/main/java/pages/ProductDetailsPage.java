package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage extends CommonElementsPage {
    //The "//div[@class='product-details']" is included in all paths to ensure that the found elements are located
    //under the product details div as other names/categories/prices/etc. potentially may be added to the page later
    //e.g. recommended products and their details. "text()" is added to ensure the details are not empty.
    By productName = new By.ByXPath("//div[@class='product-details']//h2[text()]");
    By productCategory = new By.ByXPath("//div[@class='product-details']//p[contains(text(), 'Category')]");
    By productPrice = new By.ByXPath("//div[@class='product-details']//span//span[text()]");
    By productAvailability = new By.ByXPath("//div[@class='product-details']//b[contains(text(), 'Availability')]" +
            "/parent::p[text()]");
    By productCondition = new By.ByXPath("//div[@class='product-details']//b[contains(text(), 'Condition')]" +
            "/parent::p[text()]");
    By productBrand = new By.ByXPath("//div[@class='product-details']//b[contains(text(), 'Brand')]" +
            "/parent::p[text()]");

    By productCatAndSubcat = new By.ByXPath("//div[contains(@class, 'features_items')]" +
            "//h2[contains(@class, 'title')]");

    By viewCartButton = new By.ByXPath("//div[@id='cartModal']//a[@href='/view_cart']");
    By addToCartButton = new By.ByXPath("//button[contains(@class, 'btn-default cart')]");

    By quantityInput = new By.ById("quantity");

    By reviewLabel = new By.ByXPath("//a[@href=\"#reviews\"]");
    By reviewNameInput = new By.ById("name");
    By reviewEmailInput = new By.ById("email");
    By reviewTextInput = new By.ById("review");
    By submitReviewButton = new By.ById("button-review");

    By successfullReviewLabel = new By.ByXPath("//div[@id='review-section']//div[contains(@class, 'alert-success')]//span");

    public ProductDetailsPage() {
        super();
    }

    public boolean isNameVisible() {
        return isElementVisible(productName);
    }

    public boolean isCategoryVisible() {
        return isElementVisible(productCategory);
    }

    public boolean isPriceVisible() {
        return isElementVisible(productPrice);
    }

    public boolean isAvailabilityVisible() {
        return isElementVisible(productAvailability);
    }

    public boolean isConditionVisible() {
        return isElementVisible(productCondition);
    }

    public boolean isBrandVisible() {
        return isElementVisible(productBrand);
    }

    public boolean areAllDetailsVisible() {
        return isNameVisible() && isCategoryVisible() && isPriceVisible() && isAvailabilityVisible() &&
                isConditionVisible() && isBrandVisible();
    }

    public void clickViewCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        wait.until(ExpectedConditions.elementToBeClickable(viewCartButton));
        clickElementByLocator(viewCartButton);
    }

    public void enterQuantity(int quantity) {
        driver.findElement(quantityInput).clear();
        fillInput(quantityInput, Integer.toString(quantity));
    }

    public void clickAddToCart() {
        clickElementByLocator(addToCartButton);
    }

    public String getCatAndSubcat() {
        String catAndSubcat = driver.findElement(productCatAndSubcat).getText();

        return catAndSubcat.substring(0, catAndSubcat.length() - 9);
    }

    public boolean isReviewLabelVisible() {
        return isElementVisible(reviewLabel);
    }

    public boolean isReviewTextLabelCorrect(String expectedText) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Forced to use JS here as getText() returns all uppercase because of CSS
        return expectedText.equals(js.executeScript("return arguments[0].textContent;", driver.findElement(reviewLabel)));
    }

    public void fillReviewName(String name) {
        fillInput(reviewNameInput, name);
    }

    public void fillReviewEmail(String email) {
        fillInput(reviewEmailInput, email);
    }

    public void fillReviewText(String review) {
        fillInput(reviewTextInput, review);
    }

    public void fillAllReviewInputs(String name, String email, String review) {
        fillReviewName(name);
        fillReviewEmail(email);
        fillReviewText(review);
    }

    public void clickSubmitReview() {
        clickElementByLocator(submitReviewButton);
    }

    public boolean isReviewSubmittedSuccessVisible() {
        return isElementVisible(successfullReviewLabel, 2);
    }

    public boolean isReviewSubmittedTextCorrect(String expected) {
        return expected.equals(driver.findElement(successfullReviewLabel).getText());
    }
}
