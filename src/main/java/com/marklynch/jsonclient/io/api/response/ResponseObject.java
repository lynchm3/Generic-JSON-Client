package com.marklynch.jsonclient.io.api.response;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseObject {

    @JsonProperty("member")
    public ResponseSubObject member;
    @JsonProperty("ip")
    public String ip;

    @Override
    public String toString() {
        return "ResponseObject{" +
                "member=" + member + "," +
                "ip=" + ip +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ResponseObject that = (ResponseObject) o;
        return Objects.equals(member, that.member);
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseSubObject {
        @JsonProperty("subMember")
        public ResponseSubObject subMember;

        @Override
        public String toString() {
            return "ResponseSubObject{" +
                    "subMember=" + subMember +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ResponseSubObject responseSubObject = (ResponseSubObject) o;
            return Objects.equals(subMember, responseSubObject.subMember);
        }
    }
}