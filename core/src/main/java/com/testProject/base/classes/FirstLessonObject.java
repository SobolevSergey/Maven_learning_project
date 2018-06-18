package com.testProject.base.classes;

import com.testProject.base.interfaces.HasObjectFullInfo;

import java.util.Date;

public class FirstLessonObject {
    private String name;
    protected String address;
    private FirstLessonInnerObject innerObject;

    public FirstLessonObject() {
        this.innerObject = createInnerObject(null);
    }

    public FirstLessonObject(String name) {
        this.name = name;
        this.innerObject = createInnerObject(name);
    }

    private FirstLessonInnerObject createInnerObject(String mainObjectName) {
        FirstLessonInnerObject innerObject = new FirstLessonInnerObject();
        innerObject.setParent(this);
        innerObject.setObjectExtraInformation(String.format("created at %tM", new Date()));
        return innerObject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public FirstLessonInnerObject getInnerObject() {
        return innerObject;
    }

    public void setInnerObject(FirstLessonInnerObject innerObject) {
        this.innerObject = innerObject;
    }


    //Задание 3 Использование интерфейсов, внутренних классов
    public static class FirstLessonInnerObject implements HasObjectFullInfo {
        private FirstLessonObject parent;
        private String objectExtraInformation;

        public String getObjectExtraInformation() {
            return objectExtraInformation;
        }

        public void setObjectExtraInformation(String objectExtraInformation) {
            this.objectExtraInformation = objectExtraInformation;
        }

        public FirstLessonObject getParent() {
            return parent;
        }

        public void setParent(FirstLessonObject parent) {
            this.parent = parent;
        }

        public String getObjectFullInfo() {
            if (parent != null) {
                return String.format("Parent: %s. Address: %s. Extra info: %s", parent.getName(),
                        parent.getAddress(), objectExtraInformation);
            } else return null;
        }
    }
}
