package sg.edu.ntu.simple_crm;

import java.util.ArrayList;

public interface InteractionService {
  Interaction saveInteraction(Interaction interaction);

  Interaction getInteraction(Long id);

  ArrayList<Interaction> getAllInteractions();

  Interaction updateInteraction(Long id, Interaction interaction);

  void deleteInteraction(Long id);

}
