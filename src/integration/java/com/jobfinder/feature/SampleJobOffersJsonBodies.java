package com.jobfinder.feature;

interface SampleJobOffersJsonBodies {

    default String bodyWithNoJobOffers() {
        return "[]";
    }

    default String bodyWithTwoJobOffers() {
        return """
                [
                    {
                        "title": "Software Engineer",
                        "company": "TechCorp",
                        "salary": "$80,000 - $100,000",
                        "offerUrl": "https://techcorp.com/careers/12345"
                    },
                    {
                        "title": "Data Scientist",
                        "company": "DataSolutions",
                        "salary": "$90,000 - $120,000",
                        "offerUrl": "https://datasolutions.com/jobs/67890"
                    }
                ]
                """;
    }

    default String bodyWithOneJobOffer() {
        return """
                [
                    {
                        "title": "Frontend Developer",
                        "company": "WebWorks",
                        "salary": "$70,000 - $90,000",
                        "offerUrl": "https://webworks.com/opportunities/54321"
                    }
                ]
                """;
    }

    default String bodyWithThreeJobOffers() {
        return """
                [
                    {
                        "title": "Backend Developer",
                        "company": "CodeBase",
                        "salary": "$85,000 - $110,000",
                        "offerUrl": "https://codebase.com/careers/11223"
                    },
                    {
                        "title": "Full Stack Developer",
                        "company": "DevSolutions",
                        "salary": "$95,000 - $130,000",
                        "offerUrl": "https://devsolutions.com/jobs/33445"
                    },
                    {
                        "title": "Machine Learning Engineer",
                        "company": "AI Innovations",
                        "salary": "$100,000 - $150,000",
                        "offerUrl": "https://aiinnovations.com/opportunities/55667"
                    }
                ]
                """;
    }

    default String bodyWithFourJobOffers() {
        return """
                [
                    {
                        "title": "DevOps Engineer",
                        "company": "CloudNet",
                        "salary": "$90,000 - $120,000",
                        "offerUrl": "https://cloudnet.com/careers/77889"
                    },
                    {
                        "title": "QA Engineer",
                        "company": "TestWorks",
                        "salary": "$70,000 - $95,000",
                        "offerUrl": "https://testworks.com/jobs/99001"
                    },
                    {
                        "title": "UI/UX Designer",
                        "company": "DesignHub",
                        "salary": "$65,000 - $85,000",
                        "offerUrl": "https://designhub.com/opportunities/22334"
                    },
                    {
                        "title": "Product Manager",
                        "company": "Innovatech",
                        "salary": "$110,000 - $140,000",
                        "offerUrl": "https://innovatech.com/careers/44556"
                    }
                ]
                """;
    }
}
