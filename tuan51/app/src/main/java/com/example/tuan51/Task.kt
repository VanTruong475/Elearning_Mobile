package com.example.tuan51

import com.google.gson.*
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

// Response wrapper for tasks list - flexible to handle different API responses
data class TasksResponse(
    val tasks: List<Task> = emptyList()
)

// Custom deserializer to handle both array and object responses
class TasksResponseDeserializer : JsonDeserializer<TasksResponse> {
    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): TasksResponse {
        val taskListType = object : TypeToken<List<Task>>() {}.type
        
        return when {
            // If response is an array directly
            json.isJsonArray -> {
                val tasksList: List<Task> = context.deserialize(json, taskListType)
                TasksResponse(tasksList)
            }
            // If response is an object
            json.isJsonObject -> {
                val jsonObject = json.asJsonObject
                val tasksList: List<Task> = when {
                    // Check for "tasks" field
                    jsonObject.has("tasks") -> {
                        context.deserialize(jsonObject.get("tasks"), taskListType)
                    }
                    // Check for "data" field
                    jsonObject.has("data") -> {
                        context.deserialize(jsonObject.get("data"), taskListType)
                    }
                    // Otherwise return empty
                    else -> emptyList()
                }
                TasksResponse(tasksList)
            }
            else -> TasksResponse(emptyList())
        }
    }
}

data class Task(
    @SerializedName("id")
    val id: String = "",
    
    @SerializedName("title")
    val title: String = "",
    
    @SerializedName("description")
    val description: String = "",
    
    @SerializedName("status")
    val status: String = "",
    
    @SerializedName("category")
    val category: String = "",
    
    @SerializedName("priority")
    val priority: String = "",
    
    @SerializedName("dueDate")
    val dueDate: String = "",
    
    @SerializedName("subtasks")
    val subtasks: List<Subtask>? = null,
    
    @SerializedName("attachments")
    val attachments: List<Attachment>? = null
)

data class Subtask(
    @SerializedName("id")
    val id: String = "",
    
    @SerializedName("title")
    val title: String = "",
    
    @SerializedName("completed")
    val completed: Boolean = false
)

data class Attachment(
    @SerializedName("id")
    val id: String = "",
    
    @SerializedName("name")
    val name: String = "",
    
    @SerializedName("url")
    val url: String = ""
)

