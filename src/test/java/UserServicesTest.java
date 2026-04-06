import br.com.yasmin.crud.exceptions.EmailAlreadyExistsException;
import br.com.yasmin.crud.exceptions.UserNotFoundException;
import br.com.yasmin.crud.models.User;
import br.com.yasmin.crud.repository.UserRepository;
import br.com.yasmin.crud.repository.UserRepositoryInMemory;
import br.com.yasmin.crud.services.UserServices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//verificar se ele nao deveria estar em uma pasta chamada userservieces
class UserServicesTest {
    UserRepository userRepository = new UserRepositoryInMemory();
    UserServices userServices = new UserServices(userRepository);

    @Test
    void shouldThrowExceptionWhenNameIsNull(){
        User u = new User();
        u.setAge(21);
        u.setEmail("YasminEmail");
        u.setName(null);
        assertThrows(IllegalArgumentException.class, () ->
        {
            userServices.registerUser(u);
        });
    }
    @Test
    void shouldThrowExceptionWhenNameIsBlank(){
        User u = new User();
        u.setAge(21);
        u.setEmail("YasminEmail");
        u.setName("   ");
        assertThrows(IllegalArgumentException.class, () ->
        {
            userServices.registerUser(u);
        }
        );
    }
    @Test
    void shouldThrowExceptionWhenEmailIsNull(){
        User u = new User();
        u.setAge(21);
        u.setName("Yasmin");
        assertThrows(IllegalArgumentException.class, () ->
        {
            userServices.registerUser(u);
        }
        );
    }
    @Test
    void shouldThrowExceptionWhenEmailIsBlank(){
        User u = new User();
        u.setAge(21);
        u.setName("Yasmin");
        u.setEmail("  ");
        assertThrows(IllegalArgumentException.class, () ->
        {
            userServices.registerUser(u);
        }
        );
    }
    @Test
    void shouldThrowExceptionWhenAgeIsNegative()
    {
        User u = new User("Yasmin", "YasminEmail", -1);
        assertThrows(IllegalArgumentException.class, () ->
        {
            userServices.registerUser(u);
        }
        );
    }
    @Test
    void shouldThrowExceptionWhenAgeIsZero()
    {
        User u = new User("Yasmin", "YasminEmail", 0);
        assertThrows(IllegalArgumentException.class, () ->
        {
            userServices.registerUser(u);
        }
        );
    }
    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists()
    {
        User u = new User("Yasmin", "YasminEmail", 1);
        User j = new User("Yasmin2", "YasminEmail", 2);
        userRepository.save(u);
        assertThrows(EmailAlreadyExistsException.class, () ->
        {
            userServices.registerUser(j);
        });
    }
    @Test
    void shouldNotChangeAmountOfUsersWhenEmailAlreadyExists()
    {
        User u = new User("Yasmin", "YasminEmail", 1);
        User j = new User("Yasmin2", "YasminEmail", 2);
        userRepository.save(u);
        int amountOfUsersBeforeFailing = userServices.getAllUsers().size();
        assertThrows(EmailAlreadyExistsException.class, () ->
        {
            userServices.registerUser(j);
        });
        int amountOfUsersAfterFailing = userServices.getAllUsers().size();
        assertEquals(amountOfUsersBeforeFailing, amountOfUsersAfterFailing);
    }
    @Test
    void shouldThrowExceptionWhenUserToDeleteNotFound()
    {
        String nonExistingId = "99999L";
        assertThrows(UserNotFoundException.class, () ->
        {
            userServices.deleteUser(nonExistingId);
        });
    }
    @Test
    void shouldNotAffectOtherUsersWhenDeletingUser()
    {
        User u = new User("Yasmin", "YasminEmail", 1);
        User j = new User("Yasmin2", "YasminEmail2", 2);
        userServices.registerUser(u);
        userServices.registerUser(j);

        int sizeBefore = userServices.getAllUsers().size();

        User userBeforeDeletion = userServices.getUserByEmail(u.getEmail());
        String userBeforeDeletionEmail = userBeforeDeletion.getEmail();
        String userBeforeDeletionName = userBeforeDeletion.getName();
        String userBeforeDeletionId = userBeforeDeletion.getId();
        int userBeforeDeletionAge = userBeforeDeletion.getAge();
        userServices.deleteUser(j.getId());

        int sizeAfter = userServices.getAllUsers().size();

        User userAfterDeletion = userServices.getUserByEmail(u.getEmail());
        String userAfterDeletionEmail = userAfterDeletion.getEmail();
        String userAfterDeletionName = userAfterDeletion.getName();
        String userAfterDeletionId = userAfterDeletion.getId();
        int userAfterDeletionAge = userAfterDeletion.getAge();
        assertEquals(sizeBefore - 1, sizeAfter);
        assertEquals(userBeforeDeletionAge, userAfterDeletionAge);
        assertEquals(userBeforeDeletionEmail, userAfterDeletionEmail);
        assertEquals(userBeforeDeletionName, userAfterDeletionName);
        assertEquals(userBeforeDeletionId, userAfterDeletionId);
        assertThrows(UserNotFoundException.class, () ->
        {
            userServices.getUserByEmail(j.getEmail());
        });



    }
    @Test
   void shouldNotBeAbleToUpdateNameOfNonExistingUser()
    {
        String nonExistingId = "99";
        String newName = "NewName";
        assertThrows(UserNotFoundException.class, () ->
        {
            userServices.updateUserName(nonExistingId,newName);
        });
    }
    @Test
    void shouldNotChangeEmailIfOnlyNameChanged()
    {
        User u = new User("Yasmin", "YasminEmail", 1);
        userServices.registerUser(u);
        String emailBefore = u.getEmail();
        userServices.updateUserName(u.getId(), "NewName");
        User u2 = userServices.getUserByEmail(u.getEmail());
        String emailAfter = u2.getEmail();
        assertEquals(emailBefore, emailAfter);

    }
    @Test
    void shouldThrowExceptionWhenNewNameIsNull()
    {
        User u = new User("Yasmin", "YasminEmail", 1);
        userServices.registerUser(u);
        assertThrows(IllegalArgumentException.class, () ->
        {
           User j = userServices.getUserByEmail(u.getEmail());
           userServices.updateUserName(j.getId(), null);
        });
    }
    @Test
    void shouldThrowExceptionWhenNewNameIsBlank()
    {

        User u = new User("Yasmin", "YasminEmail", 1);
        userServices.registerUser(u);
        assertThrows(IllegalArgumentException.class, () ->
        {
            User j = userServices.getUserByEmail(u.getEmail());
            userServices.updateUserName(j.getId(), "  ");
        });
    }
    @Test
    void shouldThrowExceptionWhenEmailDoesNotExist()
    {
        String nonExistingEmail = "@gmail.com";
        assertThrows(UserNotFoundException.class, () ->
        {
            userServices.getUserByEmail(nonExistingEmail);
        });
    }
}