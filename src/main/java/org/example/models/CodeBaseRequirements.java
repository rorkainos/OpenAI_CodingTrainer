package org.example.models;

/*
 * The users input is to be collected and put into a validated JSON format.
 * This class serves to define the Structure of the JSON
 * This allows us to carefully craft a great request to Azure to build a good code base.
 *
 * rorourke
 */
public record CodeBaseRequirements(String language, String topic, String currentExperience, String readMeTopics) {
}
