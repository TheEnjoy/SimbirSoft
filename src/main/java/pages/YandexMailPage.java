package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexMailPage {
    @FindBy(css = "input[placeholder*=\"Поиск\"]")
    public WebElement searchInput;

    @FindBy(css = "button[class$=\"search-input__form-button\"]")
    public WebElement findButton;

    @FindBy(css = "div[class*=\"b-search-not-found\"]")
    public WebElement notFound;

    @FindBy(css = "span[class*=\"mail-MessagesSearchInfo-Title_misc\"]")
    public WebElement countMail;

    @FindBy(css = "a[title*=\"Написать\"]")
    public WebElement writeMailButton;

    @FindBy(css = ".ComposeRecipients-ToField > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1)")
    public WebElement sendToInput;

    @FindBy(css = "div.ComposeContactsList-Item:nth-child(1)")
    public WebElement contactMailForSend;

    @FindBy(css = ".composeTextField")
    public WebElement themeMailForSend;

    @FindBy(css = ".cke_wysiwyg_div")
    public WebElement bodyMailForSend;

    @FindBy(css = ".ComposeControlPanelButton-Button_action")
    public WebElement buttonForSendMail;

    @FindBy(css = "html#nb-1.mail-Page.mail-Page_2pane.m-locale_ru.notranslate.js-page.feature-save-to-disk-button.feature-web-enable-ps-header.feature-web-enable-react-ps-header.feature-web_enable_mail-360-no-disk-20gb.feature-yandex-plus.feature-web-ps-header.feature-web-react-ps-header.feature-web_ad_on_yes_mail_pro.feature-web_mail-pro-onboarding.feature-web_mail-360-no-disk-20gb.feature-web-b2c-domain.feature-web-b2c-domain-promos.feature-web-left-column-pro-subscription-widget.feature-web-mailbox-backup.feature-web_turn-off-ns-compose-promo.feature-web_aligned-direct.feature-web_spam-new-icon.feature-web_captcha-type-wave.feature-web_interactive-email.feature-web-ads-mailpro-left-button-new.feature-web-ads-left-with-params.feature-web_enable_pcode_native_banner.feature-web_ads.feature-web_telegram-bot-promo.feature-web_smart-subject-popup.feature-web_smart-subject-line.feature-web_pro_button_left_column_without_label.feature-web_pro_promo_left_column.feature-web-unsubscribe-toolbar-enable.feature-web_disable-ns-compose-immediately.feature-web_disable-ns-compose.feature-speedup.blobconstructor.blob-constructor.geolocation.filereader.classlist.cssgradients.supports.cssanimations.borderimage.csstransforms.csstransforms3d.csstransitions.cssmask.no-highresdisplay.no-is-pdd.draganddrop.draganddrop-files.input-multiple.classlist-second-arg.no-msie.no-webkit.no-opera.mozilla.no-edge.no-safari.no-ie9.no-ie10.no-ie11.no-ielt11.no-ios.no-mac.with-banner.m-loaded.theme-colorful.m-skin-blue.m-skin-blue-blue body.mail-Page-Body.js-mail-Page-Body.pointerfocus div.ns-view-app.ns-view-id-20.mail-App.js-mail-App.mail-App-Search.mail-App-Advanced-Search div.mail-App-Content.js-mail-app-content div.mail-Layout.js-mail-layout.mail-Layout_2pane.ui-resizable.messages_avatars div.mail-Layout-Inner div.mail-Layout-Main.js-mail-layout-content div.mail-Layout-Content div.ns-view-right-box.ns-view-id-27.mail-Layout-Panes.js-mail-Layout-Panes.mail-Layout-Panes_2pane div.ns-view-messages-list.ns-view-id-67.mail-Layout-Pane.mail-Layout-Pane_listing.js-layout-first-pane div.ns-view-messages-list-box.ns-view-id-68.mail-Layout-Pane-Wrap.js-messages-scroll-area div.ns-view-messages-wrap.ns-view-id-69.js-Mail-MessagesWrap div.ns-view-messages.ns-view-id-70.js-action-context div.ns-view-container-desc.mail-MessagesList.js-messages-list div.ns-view-messages-item-wrap.ns-view-id-124.mail-MessageSnippet-Wrap.js-messages-item-checkbox-controller.js-cbt-item.js-message-snippet-wrap.js-messages-item.mid-175640385467449445.js-message-wrap-175640385467449445.is-checked div.ns-view-messages-item-box.ns-view-id-125 div.ns-view-messages-item.ns-view-id-126.js-message.ns-action.js-message-id-175640385467449445.mid-175640385467449445.no-user-select div.mail-MessageSnippet-Wrapper a.mail-MessageSnippet.js-message-snippet.toggles-svgicon-on-important.toggles-svgicon-on-unread.is-unread div.mail-MessageSnippet-Content span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_body.js-message-snippet-body div.mail-MessageSnippet-Item_content-row span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_text.js-message-snippet-text span.mail-MessageSnippet-Item.mail-MessageSnippet-Item_firstline.js-message-snippet-firstline")
    public WebElement lastFindMail;


    public static final String MY_MAIL = "akuz0419.1@yandex.ru";
    public YandexMailPage(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 15, 50);
        PageFactory.initElements(driver, this);
    }

    public String textForSend(String numberText){
        int number = Integer.parseInt(numberText);
        int lastDigit = number % 100 / 10;
        if (lastDigit == 1)
        {
            return String.format("Найдено %s писем", numberText);
        }
        switch (number % 10)
        {
            case 1:
                return String.format("Найдено %s письмо", numberText);
            case 2:
            case 3:
            case 4:
                return String.format("Найдено %s письма", numberText);
            default:
                return String.format("Найдено %s писем", numberText);
        }
    }
}
