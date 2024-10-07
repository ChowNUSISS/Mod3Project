package sg.edu.ntu.simple_crm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class InteractionServiceImpl implements InteractionService {

  private InteractionRepository interactionRepository;

  public InteractionServiceImpl(InteractionRepository interactionRepository) {
    this.interactionRepository = interactionRepository;
  }

  @Override
  public Interaction saveInteraction(Interaction interaction) {
    return interactionRepository.save(interaction);
  }

  @Override
  public Interaction getInteraction(Long id) {
    return interactionRepository.findById(id).get();
  }

  @Override
  public ArrayList<Interaction> getAllInteractions() {
    List<Interaction> allInteractions = interactionRepository.findAll();
    return (ArrayList<Interaction>) allInteractions;
  }

  @Override
  public Interaction updateInteraction(Long id, Interaction interaction) {
    Interaction interactionToUpdate = interactionRepository.findById(id).get();
    interactionToUpdate.setRemarks(interaction.getRemarks());
    interactionToUpdate.setInteractionDate(interaction.getInteractionDate());
    return interactionRepository.save(interactionToUpdate);
  }

  @Override
  public void deleteInteraction(Long id) {
    interactionRepository.deleteById(id);
  }

}
