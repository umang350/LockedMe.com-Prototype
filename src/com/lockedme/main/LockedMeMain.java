package com.lockedme.main;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.lockedme.exception.AppFileException;
import com.lockedme.model.AppFile;
import com.lockedme.service.AppFileService;
import com.lockedme.service.impl.AppFileServiceImpl;

public class LockedMeMain {

	public static void main(String[] args) {
		System.out.println("\tWelcome to LockedMe.com Prototype");
		System.out.println("\t  Developed By: Umang Chauhan");
		System.out.println("-----------------------------------------------------");
		int ch=0;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		AppFileService service = new AppFileServiceImpl();
		try {
			service.getStoredData();
		} catch (AppFileException e2) {
			System.out.println(e2.getMessage());
		}
		do {
			System.out.println("\nLockedMe Menu");
			System.out.println("=================================");
			System.out.println("1) Showcase current files in App (In Ascending Order)");
			System.out.println("2) Enter Interface to Add, Search, Remove files from App");
			System.out.println("3) EXIT APPLICATION");
			System.out.println("Please Enter your appropriate choice(1-3)");
			
			try {
				ch = Integer.parseInt(scanner.nextLine());
			}catch(NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			switch (ch) {
			case 1:
				try {
					List<AppFile> appFilesList=service.getAllAppFiles();
					if(appFilesList!=null && appFilesList.size()>0) {
						System.out.println("Currently there are "+appFilesList.size()+ " files in the LockedMe.com App.\nAll Files Listed below in ascending order:- ");
						for(AppFile s:appFilesList) {
							System.out.println(s.getFileName());
						}
					}
				} catch (AppFileException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 2:
				do {
				System.out.println("\nManage Application Files Menu");
				System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
				System.out.println("1) Add a file");
				System.out.println("2) Delete a file");
				System.out.println("3) Search Files");
				System.out.println("4) Return to Main Menu");
				System.out.println("Please Enter your appropriate choice(1-4)");
				try {
					ch = Integer.parseInt(scanner.nextLine());
				}catch(NumberFormatException e) {
					
				}
				switch (ch) {
				case 1:
					System.out.println("Enter File Details Below .....");
					AppFile ap = new AppFile();
					System.out.println("Enter File Name");
					ap.setFileName(scanner.nextLine());
					System.out.println("Enter File's Type (Text, Image, Video)");
					ap.setFileType(scanner.nextLine());
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");  
					LocalDateTime now = LocalDateTime.now();  
					ap.setDateAdded(dtf.format(now));
					try {
						ap = service.addAppFile(ap);
						System.out.println("File Added to App with Details "+ap);
					} catch (AppFileException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 2:
					System.out.println("Please enter File Name to be deleted:");
					String filenameToDel = scanner.nextLine();
					try {
						service.deleteAppFileByName(filenameToDel);
						System.out.println("File Sucessfully Deleted");
					} catch (AppFileException e1) {
						System.out.println(e1.getMessage());
					}
					break;
				case 3:
					do {
						System.out.println("\nSearch File Menu");
						System.out.println("======================");
						System.out.println("1) Search by File Name");
						System.out.println("2) Search by File Type (Text,Image,Video)");
						System.out.println("3) Search by File Date (Enter in dd/mm/yyyy format)");
						System.out.println("4) Back to Manage Files Menu");
						System.out.println("Please Enter your appropriate choice(1-4)");
						try {
							ch = Integer.parseInt(scanner.nextLine());
						}catch(NumberFormatException e) {
							System.out.println(e.getMessage());
						}
						switch (ch) {
						case 1:
							System.out.println("Please enter File Name to search");
							String filenameToS = scanner.nextLine();
							try {
								AppFile searched = service.getAppFileByName(filenameToS);
								System.out.println("Found file in the Application called: "+searched);
							} catch (AppFileException e1) {
								System.out.println(e1.getMessage());
							}
							break;
						case 2:
							System.out.println("Please enter File Type to search (Text,Image,Video)");
							String fileTypeToS = scanner.nextLine();
							try {
								List<AppFile> searched = service.getAppFilesByType(fileTypeToS);
								System.out.println("Files of Type= "+fileTypeToS+ " are listed below:");
								for(AppFile s:searched) {
									System.out.println(s.getFileName());
								}
							} catch (AppFileException e1) {
								System.out.println(e1.getMessage());
							}
							break;
						case 3:
							System.out.println("Please enter File Date to search (dd/mm/yyyy)");
							String fileDateToS = scanner.nextLine();
							try {
								List<AppFile> searched = service.getAppFilesByDateAdded(fileDateToS);
								System.out.println("Files added on Date= "+fileDateToS+ " are listed below:");
								for(AppFile s:searched) {
									System.out.println(s.getFileName());
								}
							} catch (AppFileException e1) {
								System.out.println(e1.getMessage());
							}
							break;
						case 4:
							break;
						
						default:
							System.out.println("Entered Invalid choice it should be between 1-4 only");
							break;
						}
					}while(ch!=4);
					ch=0;
					break;
				case 4:
					break;
				default:
					System.out.println("Entered Invalid choice it should be between 1-4 only");
					break;
				}
				}while(ch!=4);
				ch=0;
				break;
			case 3:
				try {
					service.storeData();
				} catch (AppFileException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("Thank you for using the Application LockedMe.com developed by Umang Chauhan. \nHope to see you soon!");
				break;
			default:
				System.out.println("Entered Invalid choice it should be between 1-3 only");
				break;
			}
		}while(ch!=3);

	}

}
