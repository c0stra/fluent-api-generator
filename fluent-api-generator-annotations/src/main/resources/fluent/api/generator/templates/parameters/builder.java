{% set productType = (method.isConstructor) ? (method.declaringClass) : (method.type) %}
{% set packageName = (packageName == "") ? method.declaringClass.packageName : packageName %}
{% set className = (className == "") ? concat(method.declaringClass.simpleName, (method.isConstructor) ? "" : capitalize(method.name), capitalize(methodName), "er") : className %}
{% set typeParameterList = (method.isConstructor) ? productType.parameters : ((method.isStatic) ? method.typeVariables : merge(method.declaringClass.parameters, method.typeVariables)) %}
{% set classParameters = (empty(typeParameterList)) ? "" : concat("<", join(typeParameterList, ", "), ">") %}
package {{ packageName }};
import javax.annotation.Generated;
import fluent.api.End;

@Generated("Generated code using {{ templatePath }}")
public class {{ className }}{% if not empty(typeParameterList) %}<{% for t in typeParameterList %}{% if loop.first %}{% else %}, {% endif %}{{ t.declaration }}{% endfor %}>{% endif %} {

{% for parameter in method.parameters %}
    private {{ parameter.type }} {{ parameter.name }};
{% endfor %}
{% if method.isConstructor or method.isStatic %}{% else %}
    private final {{ method.declaringClass }} factory;

    public {{ className }}({{ method.declaringClass }} factory) {
        this.factory = factory;
    }
{% endif %}
{% for parameter in method.parameters %}
    public {{ className }}{{ classParameters }} {{ parameter.name }}({{ parameter.type }} value) {
        this.{{ parameter.name }} = value;
        return this;
    }
{% endfor %}
    @End
    public {{ productType }} {{ methodName }}() {
        {% if productType != "void" %}return {% endif %}{%
        if method.isConstructor
            %}new {{ method.declaringClass }}{%
        elseif method.isStatic
            %}{{ method.declaringClass.raw }}.{{ method.name }}{%
        else
            %}factory.{{ method.name }}{%
        endif
        %}({{ join(method.parameters, ", ") }});
    }
}
