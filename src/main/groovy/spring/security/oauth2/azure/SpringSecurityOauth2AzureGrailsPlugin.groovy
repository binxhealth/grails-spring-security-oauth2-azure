package spring.security.oauth2.azure

import grails.plugin.springsecurity.oauth2.SpringSecurityOauth2BaseService
import grails.plugin.springsecurity.oauth2.exception.OAuth2Exception
import grails.plugins.*

class SpringSecurityOauth2AzureGrailsPlugin extends Plugin {

    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "3.3.10 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views",
        "grails-app/assets",
        "grails-app/controllers",
        "grails-app/init"
    ]

    // TODO Fill in these fields
    def title = "Spring Security Oauth2 - Microsoft Azure AD Extension" // Headline display name of the plugin
    def author ="Maura Warner"
    def authorEmail = "maura.warner@mybinxhealth.com"
    def description = "Grails 3 spring-security-oauth2 plugin extension for Microsoft Azure AD"

    def profiles = ['web']

    // URL to the plugin's documentation
    def documentation = "https://github.com/binxhealth/spring-security-oauth2-azure/blob/master/README.md"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
    def organization = [ name: "Binx Health", url: "http://mybinxhealth.com" ]

    // Location of the plugin's issue tracker.
    def issueManagement = [ system: "GitHub", url: "https://github.com/binxhealth/spring-security-oauth2-azure/issues" ]

    // Online location of the plugin's browseable source code.
    def scm = [ url: "https://github.com/binxhealth/spring-security-oauth2-azure" ]

    void doWithApplicationContext() {
        SpringSecurityOauth2BaseService oAuth2BaseService = grailsApplication.mainContext.getBean(
            'springSecurityOauth2BaseService') as SpringSecurityOauth2BaseService
        AzureOauth2ProviderService oath2ProviderService = grailsApplication.mainContext.getBean(
            'azureOauth2ProviderService') as AzureOauth2ProviderService
        try {
            oAuth2BaseService.registerProvider(oath2ProviderService)
        } catch (OAuth2Exception exception) {
            log.error("There was an oAuth2Exception", exception)
            log.error("OAuth2 Azure not loaded")
        }
    }

}
