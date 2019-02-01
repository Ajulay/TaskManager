
package com.ajulay.endpoint;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.ajulay.endpoint package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CreateAssignee_QNAME = new QName("http://endpoint.ajulay.com/", "createAssignee");
    private final static QName _CreateAssigneeResponse_QNAME = new QName("http://endpoint.ajulay.com/", "createAssigneeResponse");
    private final static QName _DeleteAssignee_QNAME = new QName("http://endpoint.ajulay.com/", "deleteAssignee");
    private final static QName _DeleteAssigneeResponse_QNAME = new QName("http://endpoint.ajulay.com/", "deleteAssigneeResponse");
    private final static QName _FindAllAssignee_QNAME = new QName("http://endpoint.ajulay.com/", "findAllAssignee");
    private final static QName _FindAllAssigneeResponse_QNAME = new QName("http://endpoint.ajulay.com/", "findAllAssigneeResponse");
    private final static QName _FindAssigneeAllByUserId_QNAME = new QName("http://endpoint.ajulay.com/", "findAssigneeAllByUserId");
    private final static QName _FindAssigneeAllByUserIdResponse_QNAME = new QName("http://endpoint.ajulay.com/", "findAssigneeAllByUserIdResponse");
    private final static QName _GetById_QNAME = new QName("http://endpoint.ajulay.com/", "getById");
    private final static QName _GetByIdResponse_QNAME = new QName("http://endpoint.ajulay.com/", "getByIdResponse");
    private final static QName _Merge_QNAME = new QName("http://endpoint.ajulay.com/", "merge");
    private final static QName _MergeResponse_QNAME = new QName("http://endpoint.ajulay.com/", "mergeResponse");
    private final static QName _UpdateAssignee_QNAME = new QName("http://endpoint.ajulay.com/", "updateAssignee");
    private final static QName _UpdateAssigneeResponse_QNAME = new QName("http://endpoint.ajulay.com/", "updateAssigneeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ajulay.endpoint
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateAssignee }
     */
    public CreateAssignee createCreateAssignee() {
        return new CreateAssignee();
    }

    /**
     * Create an instance of {@link CreateAssigneeResponse }
     */
    public CreateAssigneeResponse createCreateAssigneeResponse() {
        return new CreateAssigneeResponse();
    }

    /**
     * Create an instance of {@link DeleteAssignee }
     */
    public DeleteAssignee createDeleteAssignee() {
        return new DeleteAssignee();
    }

    /**
     * Create an instance of {@link DeleteAssigneeResponse }
     */
    public DeleteAssigneeResponse createDeleteAssigneeResponse() {
        return new DeleteAssigneeResponse();
    }

    /**
     * Create an instance of {@link FindAllAssignee }
     */
    public FindAllAssignee createFindAllAssignee() {
        return new FindAllAssignee();
    }

    /**
     * Create an instance of {@link FindAllAssigneeResponse }
     */
    public FindAllAssigneeResponse createFindAllAssigneeResponse() {
        return new FindAllAssigneeResponse();
    }

    /**
     * Create an instance of {@link FindAssigneeAllByUserId }
     */
    public FindAssigneeAllByUserId createFindAssigneeAllByUserId() {
        return new FindAssigneeAllByUserId();
    }

    /**
     * Create an instance of {@link FindAssigneeAllByUserIdResponse }
     */
    public FindAssigneeAllByUserIdResponse createFindAssigneeAllByUserIdResponse() {
        return new FindAssigneeAllByUserIdResponse();
    }

    /**
     * Create an instance of {@link GetById }
     */
    public GetById createGetById() {
        return new GetById();
    }

    /**
     * Create an instance of {@link GetByIdResponse }
     */
    public GetByIdResponse createGetByIdResponse() {
        return new GetByIdResponse();
    }

    /**
     * Create an instance of {@link Merge }
     */
    public Merge createMerge() {
        return new Merge();
    }

    /**
     * Create an instance of {@link MergeResponse }
     */
    public MergeResponse createMergeResponse() {
        return new MergeResponse();
    }

    /**
     * Create an instance of {@link UpdateAssignee }
     */
    public UpdateAssignee createUpdateAssignee() {
        return new UpdateAssignee();
    }

    /**
     * Create an instance of {@link UpdateAssigneeResponse }
     */
    public UpdateAssigneeResponse createUpdateAssigneeResponse() {
        return new UpdateAssigneeResponse();
    }

    /**
     * Create an instance of {@link Session }
     */
    public Session createSession() {
        return new Session();
    }

    /**
     * Create an instance of {@link Project }
     */
    public Project createProject() {
        return new Project();
    }

    /**
     * Create an instance of {@link Success }
     */
    public Success createSuccess() {
        return new Success();
    }

    /**
     * Create an instance of {@link Assignee }
     */
    public Assignee createAssignee() {
        return new Assignee();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAssignee }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "createAssignee")
    public JAXBElement<CreateAssignee> createCreateAssignee(CreateAssignee value) {
        return new JAXBElement<CreateAssignee>(_CreateAssignee_QNAME, CreateAssignee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreateAssigneeResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "createAssigneeResponse")
    public JAXBElement<CreateAssigneeResponse> createCreateAssigneeResponse(CreateAssigneeResponse value) {
        return new JAXBElement<CreateAssigneeResponse>(_CreateAssigneeResponse_QNAME, CreateAssigneeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAssignee }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "deleteAssignee")
    public JAXBElement<DeleteAssignee> createDeleteAssignee(DeleteAssignee value) {
        return new JAXBElement<DeleteAssignee>(_DeleteAssignee_QNAME, DeleteAssignee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteAssigneeResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "deleteAssigneeResponse")
    public JAXBElement<DeleteAssigneeResponse> createDeleteAssigneeResponse(DeleteAssigneeResponse value) {
        return new JAXBElement<DeleteAssigneeResponse>(_DeleteAssigneeResponse_QNAME, DeleteAssigneeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllAssignee }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "findAllAssignee")
    public JAXBElement<FindAllAssignee> createFindAllAssignee(FindAllAssignee value) {
        return new JAXBElement<FindAllAssignee>(_FindAllAssignee_QNAME, FindAllAssignee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAllAssigneeResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "findAllAssigneeResponse")
    public JAXBElement<FindAllAssigneeResponse> createFindAllAssigneeResponse(FindAllAssigneeResponse value) {
        return new JAXBElement<FindAllAssigneeResponse>(_FindAllAssigneeResponse_QNAME, FindAllAssigneeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAssigneeAllByUserId }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "findAssigneeAllByUserId")
    public JAXBElement<FindAssigneeAllByUserId> createFindAssigneeAllByUserId(FindAssigneeAllByUserId value) {
        return new JAXBElement<FindAssigneeAllByUserId>(_FindAssigneeAllByUserId_QNAME, FindAssigneeAllByUserId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindAssigneeAllByUserIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "findAssigneeAllByUserIdResponse")
    public JAXBElement<FindAssigneeAllByUserIdResponse> createFindAssigneeAllByUserIdResponse(FindAssigneeAllByUserIdResponse value) {
        return new JAXBElement<FindAssigneeAllByUserIdResponse>(_FindAssigneeAllByUserIdResponse_QNAME, FindAssigneeAllByUserIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetById }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "getById")
    public JAXBElement<GetById> createGetById(GetById value) {
        return new JAXBElement<GetById>(_GetById_QNAME, GetById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetByIdResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "getByIdResponse")
    public JAXBElement<GetByIdResponse> createGetByIdResponse(GetByIdResponse value) {
        return new JAXBElement<GetByIdResponse>(_GetByIdResponse_QNAME, GetByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Merge }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "merge")
    public JAXBElement<Merge> createMerge(Merge value) {
        return new JAXBElement<Merge>(_Merge_QNAME, Merge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "mergeResponse")
    public JAXBElement<MergeResponse> createMergeResponse(MergeResponse value) {
        return new JAXBElement<MergeResponse>(_MergeResponse_QNAME, MergeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAssignee }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "updateAssignee")
    public JAXBElement<UpdateAssignee> createUpdateAssignee(UpdateAssignee value) {
        return new JAXBElement<UpdateAssignee>(_UpdateAssignee_QNAME, UpdateAssignee.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateAssigneeResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://endpoint.ajulay.com/", name = "updateAssigneeResponse")
    public JAXBElement<UpdateAssigneeResponse> createUpdateAssigneeResponse(UpdateAssigneeResponse value) {
        return new JAXBElement<UpdateAssigneeResponse>(_UpdateAssigneeResponse_QNAME, UpdateAssigneeResponse.class, null, value);
    }

}
