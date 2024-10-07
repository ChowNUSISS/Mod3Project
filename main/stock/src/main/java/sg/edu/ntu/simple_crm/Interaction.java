package sg.edu.ntu.simple_crm;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "interaction")
@Getter
@Setter
public class Interaction {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "remarks")
  private String remarks;
  @Column(name = "interaction_date")
  private LocalDate interactionDate;

  // MANY interactions are associated with ONE customer
  @ManyToOne(optional = false)
  // JoinColumn tells Hibernate to use the customer_id column in the interaction table to store the foreign key and it should reference the id column in the customer table
  @JoinColumn(name = "customer_id", referencedColumnName = "id")
  private Customer customer;

}
