package de.azapps.mirakel.helper.error;

public enum ErrorType {
	PHOTO_NO_CAMERA, PHOTO_NO_MEDIA_DIRECTORY,

	NO_SPEACH_RECOGNITION,

	TASKS_CANNOT_FORM_LOOP, TASKS_NO_LIST, TASK_VANISHED,

	FILE_NO_ACTIVITY, FILE_URI_SYNTAX_ERROR, FILE_NOT_FOUND, FILE_TO_LARGE_FOR_THUMBNAIL, FILE_NOT_MIRAKEL_DB, FILE_NO_PERMISSION,

	BACKUP_EXPORT_ERROR, BACKUP_IMPORT_ERROR, ASTRID_ERROR,

	TASKWARRIOR_FILE_NOT_FOUND, TASKWARRIOR_COULD_NOT_DOWNLOAD, TASKWARRIOR_URL_NOT_FOUND,

	MAINACTIVITY_WRONG_POSITION,

	CONTACT_NO_CLIENT, NO_FILEMANAGER
}
