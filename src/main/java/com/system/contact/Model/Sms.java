    package com.system.contact.Model;

    import jakarta.persistence.*;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Table(name = "SMS")
    public class Sms {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "body")
        private String body;

        @Column(name = "source_number")
        private String sourceNumber;

        @Column(name = "destination_number")
        private String destinationNumber;

        @Column(name = "sent_date")
        private LocalDateTime sentDate;


        @OneToMany(mappedBy = "sms")
        private List<SmsAssociation> associations = new ArrayList<>();






        public Sms() {
        }

        public Sms(String body, String sourceNumber, String destinationNumber, LocalDateTime sentDate, List<SmsAssociation> associations) {
            this.body = body;
            this.sourceNumber = sourceNumber;
            this.destinationNumber = destinationNumber;
            this.sentDate = sentDate;
            this.associations = associations;
        }




        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getSourceNumber() {
            return sourceNumber;
        }

        public void setSourceNumber(String sourceNumber) {
            this.sourceNumber = sourceNumber;
        }

        public String getDestinationNumber() {
            return destinationNumber;
        }

        public List<SmsAssociation> getAssociations() {
            return associations;
        }

        public void setAssociations(List<SmsAssociation> associations) {
            this.associations = associations;
        }

        public void setDestinationNumber(String destinationNumber) {
            this.destinationNumber = destinationNumber;
        }

        public LocalDateTime getSentDate() {
            return sentDate;
        }

        public void setSentDate(LocalDateTime sentDate) {
            this.sentDate = sentDate;
        }



    }
