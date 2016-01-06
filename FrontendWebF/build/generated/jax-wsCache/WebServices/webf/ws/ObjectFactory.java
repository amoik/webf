
package webf.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webf.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetAllStudentsResponse_QNAME = new QName("http://webservice.webf/", "getAllStudentsResponse");
    private final static QName _DeleteRole_QNAME = new QName("http://webservice.webf/", "deleteRole");
    private final static QName _CreatePerson_QNAME = new QName("http://webservice.webf/", "createPerson");
    private final static QName _GetAllPersons_QNAME = new QName("http://webservice.webf/", "getAllPersons");
    private final static QName _GetRoleByIdResponse_QNAME = new QName("http://webservice.webf/", "getRoleByIdResponse");
    private final static QName _GetAllMemberships_QNAME = new QName("http://webservice.webf/", "getAllMemberships");
    private final static QName _CreateCourseResponse_QNAME = new QName("http://webservice.webf/", "createCourseResponse");
    private final static QName _AddResponse_QNAME = new QName("http://webservice.webf/", "addResponse");
    private final static QName _MultiplyResponse_QNAME = new QName("http://webservice.webf/", "multiplyResponse");
    private final static QName _GetAllStudents_QNAME = new QName("http://webservice.webf/", "getAllStudents");
    private final static QName _Subtract_QNAME = new QName("http://webservice.webf/", "subtract");
    private final static QName _SubtractResponse_QNAME = new QName("http://webservice.webf/", "subtractResponse");
    private final static QName _Add_QNAME = new QName("http://webservice.webf/", "add");
    private final static QName _DeletePerson_QNAME = new QName("http://webservice.webf/", "deletePerson");
    private final static QName _CreatePersonResponse_QNAME = new QName("http://webservice.webf/", "createPersonResponse");
    private final static QName _GetAllPersonsResponse_QNAME = new QName("http://webservice.webf/", "getAllPersonsResponse");
    private final static QName _LoginResponse_QNAME = new QName("http://webservice.webf/", "loginResponse");
    private final static QName _SaveCourseResponse_QNAME = new QName("http://webservice.webf/", "saveCourseResponse");
    private final static QName _CreateRoleResponse_QNAME = new QName("http://webservice.webf/", "createRoleResponse");
    private final static QName _Hello_QNAME = new QName("http://webservice.webf/", "hello");
    private final static QName _HelloResponse_QNAME = new QName("http://webservice.webf/", "helloResponse");
    private final static QName _GetRoleById_QNAME = new QName("http://webservice.webf/", "getRoleById");
    private final static QName _GetAllRolesResponse_QNAME = new QName("http://webservice.webf/", "getAllRolesResponse");
    private final static QName _GetPerson_QNAME = new QName("http://webservice.webf/", "getPerson");
    private final static QName _Login_QNAME = new QName("http://webservice.webf/", "login");
    private final static QName _CreateRole_QNAME = new QName("http://webservice.webf/", "createRole");
    private final static QName _GetAllCourses_QNAME = new QName("http://webservice.webf/", "getAllCourses");
    private final static QName _GetAllMembershipsResponse_QNAME = new QName("http://webservice.webf/", "getAllMembershipsResponse");
    private final static QName _DeleteRoleResponse_QNAME = new QName("http://webservice.webf/", "deleteRoleResponse");
    private final static QName _DivideResponse_QNAME = new QName("http://webservice.webf/", "divideResponse");
    private final static QName _SaveCourse_QNAME = new QName("http://webservice.webf/", "saveCourse");
    private final static QName _DeleteCourse_QNAME = new QName("http://webservice.webf/", "deleteCourse");
    private final static QName _CreateCourse_QNAME = new QName("http://webservice.webf/", "createCourse");
    private final static QName _GetAllRoles_QNAME = new QName("http://webservice.webf/", "getAllRoles");
    private final static QName _Multiply_QNAME = new QName("http://webservice.webf/", "multiply");
    private final static QName _GetAllCoursesResponse_QNAME = new QName("http://webservice.webf/", "getAllCoursesResponse");
    private final static QName _Divide_QNAME = new QName("http://webservice.webf/", "divide");
    private final static QName _DeleteCourseResponse_QNAME = new QName("http://webservice.webf/", "deleteCourseResponse");
    private final static QName _GetPersonResponse_QNAME = new QName("http://webservice.webf/", "getPersonResponse");
    private final static QName _DeletePersonResponse_QNAME = new QName("http://webservice.webf/", "deletePersonResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webf.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateCourse }
     * 
     */
    public CreateCourse createCreateCourse() {
        return new CreateCourse();
    }

    /**
     * Create an instance of {@link GetAllRoles }
     * 
     */
    public GetAllRoles createGetAllRoles() {
        return new GetAllRoles();
    }

    /**
     * Create an instance of {@link DeleteCourse }
     * 
     */
    public DeleteCourse createDeleteCourse() {
        return new DeleteCourse();
    }

    /**
     * Create an instance of {@link CreateRole }
     * 
     */
    public CreateRole createCreateRole() {
        return new CreateRole();
    }

    /**
     * Create an instance of {@link GetAllCourses }
     * 
     */
    public GetAllCourses createGetAllCourses() {
        return new GetAllCourses();
    }

    /**
     * Create an instance of {@link GetAllMembershipsResponse }
     * 
     */
    public GetAllMembershipsResponse createGetAllMembershipsResponse() {
        return new GetAllMembershipsResponse();
    }

    /**
     * Create an instance of {@link DeleteRoleResponse }
     * 
     */
    public DeleteRoleResponse createDeleteRoleResponse() {
        return new DeleteRoleResponse();
    }

    /**
     * Create an instance of {@link DivideResponse }
     * 
     */
    public DivideResponse createDivideResponse() {
        return new DivideResponse();
    }

    /**
     * Create an instance of {@link SaveCourse }
     * 
     */
    public SaveCourse createSaveCourse() {
        return new SaveCourse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link GetPersonResponse }
     * 
     */
    public GetPersonResponse createGetPersonResponse() {
        return new GetPersonResponse();
    }

    /**
     * Create an instance of {@link Divide }
     * 
     */
    public Divide createDivide() {
        return new Divide();
    }

    /**
     * Create an instance of {@link DeleteCourseResponse }
     * 
     */
    public DeleteCourseResponse createDeleteCourseResponse() {
        return new DeleteCourseResponse();
    }

    /**
     * Create an instance of {@link Multiply }
     * 
     */
    public Multiply createMultiply() {
        return new Multiply();
    }

    /**
     * Create an instance of {@link GetAllCoursesResponse }
     * 
     */
    public GetAllCoursesResponse createGetAllCoursesResponse() {
        return new GetAllCoursesResponse();
    }

    /**
     * Create an instance of {@link Add }
     * 
     */
    public Add createAdd() {
        return new Add();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link GetAllPersonsResponse }
     * 
     */
    public GetAllPersonsResponse createGetAllPersonsResponse() {
        return new GetAllPersonsResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GetAllMemberships }
     * 
     */
    public GetAllMemberships createGetAllMemberships() {
        return new GetAllMemberships();
    }

    /**
     * Create an instance of {@link CreateCourseResponse }
     * 
     */
    public CreateCourseResponse createCreateCourseResponse() {
        return new CreateCourseResponse();
    }

    /**
     * Create an instance of {@link AddResponse }
     * 
     */
    public AddResponse createAddResponse() {
        return new AddResponse();
    }

    /**
     * Create an instance of {@link MultiplyResponse }
     * 
     */
    public MultiplyResponse createMultiplyResponse() {
        return new MultiplyResponse();
    }

    /**
     * Create an instance of {@link GetAllStudents }
     * 
     */
    public GetAllStudents createGetAllStudents() {
        return new GetAllStudents();
    }

    /**
     * Create an instance of {@link Subtract }
     * 
     */
    public Subtract createSubtract() {
        return new Subtract();
    }

    /**
     * Create an instance of {@link SubtractResponse }
     * 
     */
    public SubtractResponse createSubtractResponse() {
        return new SubtractResponse();
    }

    /**
     * Create an instance of {@link CreatePerson }
     * 
     */
    public CreatePerson createCreatePerson() {
        return new CreatePerson();
    }

    /**
     * Create an instance of {@link GetAllPersons }
     * 
     */
    public GetAllPersons createGetAllPersons() {
        return new GetAllPersons();
    }

    /**
     * Create an instance of {@link GetRoleByIdResponse }
     * 
     */
    public GetRoleByIdResponse createGetRoleByIdResponse() {
        return new GetRoleByIdResponse();
    }

    /**
     * Create an instance of {@link GetAllStudentsResponse }
     * 
     */
    public GetAllStudentsResponse createGetAllStudentsResponse() {
        return new GetAllStudentsResponse();
    }

    /**
     * Create an instance of {@link DeleteRole }
     * 
     */
    public DeleteRole createDeleteRole() {
        return new DeleteRole();
    }

    /**
     * Create an instance of {@link GetRoleById }
     * 
     */
    public GetRoleById createGetRoleById() {
        return new GetRoleById();
    }

    /**
     * Create an instance of {@link GetAllRolesResponse }
     * 
     */
    public GetAllRolesResponse createGetAllRolesResponse() {
        return new GetAllRolesResponse();
    }

    /**
     * Create an instance of {@link GetPerson }
     * 
     */
    public GetPerson createGetPerson() {
        return new GetPerson();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link SaveCourseResponse }
     * 
     */
    public SaveCourseResponse createSaveCourseResponse() {
        return new SaveCourseResponse();
    }

    /**
     * Create an instance of {@link CreateRoleResponse }
     * 
     */
    public CreateRoleResponse createCreateRoleResponse() {
        return new CreateRoleResponse();
    }

    /**
     * Create an instance of {@link Role }
     * 
     */
    public Role createRole() {
        return new Role();
    }

    /**
     * Create an instance of {@link PersonCourseMembershipId }
     * 
     */
    public PersonCourseMembershipId createPersonCourseMembershipId() {
        return new PersonCourseMembershipId();
    }

    /**
     * Create an instance of {@link Course }
     * 
     */
    public Course createCourse() {
        return new Course();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link PersonCourseMembership }
     * 
     */
    public PersonCourseMembership createPersonCourseMembership() {
        return new PersonCourseMembership();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudentsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllStudentsResponse")
    public JAXBElement<GetAllStudentsResponse> createGetAllStudentsResponse(GetAllStudentsResponse value) {
        return new JAXBElement<GetAllStudentsResponse>(_GetAllStudentsResponse_QNAME, GetAllStudentsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "deleteRole")
    public JAXBElement<DeleteRole> createDeleteRole(DeleteRole value) {
        return new JAXBElement<DeleteRole>(_DeleteRole_QNAME, DeleteRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "createPerson")
    public JAXBElement<CreatePerson> createCreatePerson(CreatePerson value) {
        return new JAXBElement<CreatePerson>(_CreatePerson_QNAME, CreatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPersons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllPersons")
    public JAXBElement<GetAllPersons> createGetAllPersons(GetAllPersons value) {
        return new JAXBElement<GetAllPersons>(_GetAllPersons_QNAME, GetAllPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleByIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getRoleByIdResponse")
    public JAXBElement<GetRoleByIdResponse> createGetRoleByIdResponse(GetRoleByIdResponse value) {
        return new JAXBElement<GetRoleByIdResponse>(_GetRoleByIdResponse_QNAME, GetRoleByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMemberships }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllMemberships")
    public JAXBElement<GetAllMemberships> createGetAllMemberships(GetAllMemberships value) {
        return new JAXBElement<GetAllMemberships>(_GetAllMemberships_QNAME, GetAllMemberships.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCourseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "createCourseResponse")
    public JAXBElement<CreateCourseResponse> createCreateCourseResponse(CreateCourseResponse value) {
        return new JAXBElement<CreateCourseResponse>(_CreateCourseResponse_QNAME, CreateCourseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "addResponse")
    public JAXBElement<AddResponse> createAddResponse(AddResponse value) {
        return new JAXBElement<AddResponse>(_AddResponse_QNAME, AddResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiplyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "multiplyResponse")
    public JAXBElement<MultiplyResponse> createMultiplyResponse(MultiplyResponse value) {
        return new JAXBElement<MultiplyResponse>(_MultiplyResponse_QNAME, MultiplyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllStudents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllStudents")
    public JAXBElement<GetAllStudents> createGetAllStudents(GetAllStudents value) {
        return new JAXBElement<GetAllStudents>(_GetAllStudents_QNAME, GetAllStudents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Subtract }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "subtract")
    public JAXBElement<Subtract> createSubtract(Subtract value) {
        return new JAXBElement<Subtract>(_Subtract_QNAME, Subtract.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubtractResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "subtractResponse")
    public JAXBElement<SubtractResponse> createSubtractResponse(SubtractResponse value) {
        return new JAXBElement<SubtractResponse>(_SubtractResponse_QNAME, SubtractResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Add }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "add")
    public JAXBElement<Add> createAdd(Add value) {
        return new JAXBElement<Add>(_Add_QNAME, Add.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "createPersonResponse")
    public JAXBElement<CreatePersonResponse> createCreatePersonResponse(CreatePersonResponse value) {
        return new JAXBElement<CreatePersonResponse>(_CreatePersonResponse_QNAME, CreatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllPersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllPersonsResponse")
    public JAXBElement<GetAllPersonsResponse> createGetAllPersonsResponse(GetAllPersonsResponse value) {
        return new JAXBElement<GetAllPersonsResponse>(_GetAllPersonsResponse_QNAME, GetAllPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCourseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "saveCourseResponse")
    public JAXBElement<SaveCourseResponse> createSaveCourseResponse(SaveCourseResponse value) {
        return new JAXBElement<SaveCourseResponse>(_SaveCourseResponse_QNAME, SaveCourseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "createRoleResponse")
    public JAXBElement<CreateRoleResponse> createCreateRoleResponse(CreateRoleResponse value) {
        return new JAXBElement<CreateRoleResponse>(_CreateRoleResponse_QNAME, CreateRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRoleById }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getRoleById")
    public JAXBElement<GetRoleById> createGetRoleById(GetRoleById value) {
        return new JAXBElement<GetRoleById>(_GetRoleById_QNAME, GetRoleById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRolesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllRolesResponse")
    public JAXBElement<GetAllRolesResponse> createGetAllRolesResponse(GetAllRolesResponse value) {
        return new JAXBElement<GetAllRolesResponse>(_GetAllRolesResponse_QNAME, GetAllRolesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getPerson")
    public JAXBElement<GetPerson> createGetPerson(GetPerson value) {
        return new JAXBElement<GetPerson>(_GetPerson_QNAME, GetPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateRole }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "createRole")
    public JAXBElement<CreateRole> createCreateRole(CreateRole value) {
        return new JAXBElement<CreateRole>(_CreateRole_QNAME, CreateRole.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCourses }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllCourses")
    public JAXBElement<GetAllCourses> createGetAllCourses(GetAllCourses value) {
        return new JAXBElement<GetAllCourses>(_GetAllCourses_QNAME, GetAllCourses.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllMembershipsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllMembershipsResponse")
    public JAXBElement<GetAllMembershipsResponse> createGetAllMembershipsResponse(GetAllMembershipsResponse value) {
        return new JAXBElement<GetAllMembershipsResponse>(_GetAllMembershipsResponse_QNAME, GetAllMembershipsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRoleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "deleteRoleResponse")
    public JAXBElement<DeleteRoleResponse> createDeleteRoleResponse(DeleteRoleResponse value) {
        return new JAXBElement<DeleteRoleResponse>(_DeleteRoleResponse_QNAME, DeleteRoleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivideResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "divideResponse")
    public JAXBElement<DivideResponse> createDivideResponse(DivideResponse value) {
        return new JAXBElement<DivideResponse>(_DivideResponse_QNAME, DivideResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCourse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "saveCourse")
    public JAXBElement<SaveCourse> createSaveCourse(SaveCourse value) {
        return new JAXBElement<SaveCourse>(_SaveCourse_QNAME, SaveCourse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCourse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "deleteCourse")
    public JAXBElement<DeleteCourse> createDeleteCourse(DeleteCourse value) {
        return new JAXBElement<DeleteCourse>(_DeleteCourse_QNAME, DeleteCourse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateCourse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "createCourse")
    public JAXBElement<CreateCourse> createCreateCourse(CreateCourse value) {
        return new JAXBElement<CreateCourse>(_CreateCourse_QNAME, CreateCourse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllRoles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllRoles")
    public JAXBElement<GetAllRoles> createGetAllRoles(GetAllRoles value) {
        return new JAXBElement<GetAllRoles>(_GetAllRoles_QNAME, GetAllRoles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Multiply }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "multiply")
    public JAXBElement<Multiply> createMultiply(Multiply value) {
        return new JAXBElement<Multiply>(_Multiply_QNAME, Multiply.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllCoursesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getAllCoursesResponse")
    public JAXBElement<GetAllCoursesResponse> createGetAllCoursesResponse(GetAllCoursesResponse value) {
        return new JAXBElement<GetAllCoursesResponse>(_GetAllCoursesResponse_QNAME, GetAllCoursesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Divide }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "divide")
    public JAXBElement<Divide> createDivide(Divide value) {
        return new JAXBElement<Divide>(_Divide_QNAME, Divide.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteCourseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "deleteCourseResponse")
    public JAXBElement<DeleteCourseResponse> createDeleteCourseResponse(DeleteCourseResponse value) {
        return new JAXBElement<DeleteCourseResponse>(_DeleteCourseResponse_QNAME, DeleteCourseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "getPersonResponse")
    public JAXBElement<GetPersonResponse> createGetPersonResponse(GetPersonResponse value) {
        return new JAXBElement<GetPersonResponse>(_GetPersonResponse_QNAME, GetPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice.webf/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

}
