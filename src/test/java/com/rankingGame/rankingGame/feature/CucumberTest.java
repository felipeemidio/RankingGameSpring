package com.rankingGame.rankingGame.feature;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" },
features = "src/test/resources/PlayerManager.feature")
public class CucumberTest extends SpringIntegrationTest{

}
